package com.deveyk.bookstore.category.service;

import com.deveyk.bookstore.category.service.command.CategoryChangeStatusCommand;
import com.deveyk.bookstore.category.service.command.CategoryCreateCommand;
import com.deveyk.bookstore.category.service.command.CategoryListCommand;
import com.deveyk.bookstore.category.service.domain.Category;
import com.deveyk.bookstore.common.model.BsPage;

public interface ICategoryService {

    void create(CategoryCreateCommand command);

    Category getCategoryById(String categoryId);

    BsPage<Category> list(CategoryListCommand command);

    void changeStatus(CategoryChangeStatusCommand command);

    void delete(String categoryId);

    void restore(String categoryId);

}
