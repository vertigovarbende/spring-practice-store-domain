package com.deveyk.bookstore.category.service.impl;

import com.deveyk.bookstore.category.exception.CategoryAlreadyExistsException;
import com.deveyk.bookstore.category.exception.CategoryNotFoundException;
import com.deveyk.bookstore.category.exception.CategoryStatusNotSuitableForOperationException;
import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import com.deveyk.bookstore.category.model.mapper.CategoryApplicationMapper;
import com.deveyk.bookstore.category.repository.CategoryRepository;
import com.deveyk.bookstore.category.repository.entity.CategoryEntity;
import com.deveyk.bookstore.category.service.ICategoryService;
import com.deveyk.bookstore.category.service.command.CategoryChangeStatusCommand;
import com.deveyk.bookstore.category.service.command.CategoryCreateCommand;
import com.deveyk.bookstore.category.service.command.CategoryListCommand;
import com.deveyk.bookstore.category.service.domain.Category;
import com.deveyk.bookstore.common.model.BsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryApplicationMapper categoryApplicationMapper;

    @Override
    @Transactional
    public void create(CategoryCreateCommand command) {
        Objects.requireNonNull(command, "CategoryCreateCommand cannot be null");
        this.checkExistingOfCategoryName(command.name());

        Category category = Category.create(
                command.name(),
                command.description()
        );
        CategoryEntity categoryEntity = categoryApplicationMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public BsPage<Category> list(CategoryListCommand command) {
        Objects.requireNonNull(command, "CategoryListCommand cannot be null");

        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(
                command.toSpecification(CategoryEntity.class),
                command.toPageable()
        );

        List<Category> categories = categoryEntityPage
                .stream()
                .map(categoryApplicationMapper::toDomain)
                .toList();

        return BsPage.<Category>builder()
                .content(categories)
                .page(categoryEntityPage)
                .filteredBy(command.getFilter())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(String categoryId) {
        CategoryEntity categoryEntity = getCategoryEntity(categoryId);
        return categoryApplicationMapper.toDomain(categoryEntity);
    }


    @Override
    @Transactional
    public void changeStatus(CategoryChangeStatusCommand command) {
        Objects.requireNonNull(command, "CategoryChangeStatusCommand cannot be null");

        CategoryEntity categoryEntity = getCategoryEntity(command.categoryId());
        Category category = categoryApplicationMapper.toDomain(categoryEntity);

        // refactor
        if (command.targetStatus() == CategoryStatus.ACTIVE) {
            category.activate();
        } else if (command.targetStatus() == CategoryStatus.INACTIVE) {
            category.deactivate();
        } else {
            throw new CategoryStatusNotSuitableForOperationException(
                    "Category status can only be changed to ACTIVE or INACTIVE"
            );
        }

        CategoryEntity updatedCategoryEntity = categoryApplicationMapper.toEntity(category);
        categoryRepository.save(updatedCategoryEntity);
    }

    @Override
    @Transactional
    public void delete(String categoryId) {
        CategoryEntity categoryEntity = getCategoryEntity(categoryId);
        Category category = categoryApplicationMapper.toDomain(categoryEntity);

        category.markAsDeleted();

        CategoryEntity updatedCategoryEntity = categoryApplicationMapper.toEntity(category);
        categoryRepository.save(updatedCategoryEntity);

    }

    @Override
    @Transactional
    public void restore(String categoryId) {
        CategoryEntity categoryEntity = getCategoryEntity(categoryId);
        Category category = categoryApplicationMapper.toDomain(categoryEntity);

        category.restore();

        CategoryEntity updatedCategoryEntity = categoryApplicationMapper.toEntity(category);
        categoryRepository.save(updatedCategoryEntity);
    }

    // CategoryEntity
    private CategoryEntity getCategoryEntity(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + categoryId
                ));
    }

    private void checkExistingOfCategoryName(String name) {
        boolean isCategoryExistByName = categoryRepository.existsByNameIgnoreCase(name);
        if (isCategoryExistByName)
            throw new CategoryAlreadyExistsException("Category with name '" + name + "' already exists");
    }

}
