package com.deveyk.bookstore.category.model.mapper;

import com.deveyk.bookstore.category.repository.entity.CategoryEntity;
import com.deveyk.bookstore.category.service.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryApplicationMapper {

    Category toDomain(CategoryEntity categoryEntity);

    CategoryEntity toEntity(Category category);

}
