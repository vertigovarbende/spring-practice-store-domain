package com.deveyk.bookstore.category.controller.mapper;

import com.deveyk.bookstore.category.controller.request.CategoryChangeStatusRequest;
import com.deveyk.bookstore.category.controller.request.CategoryCreateRequest;
import com.deveyk.bookstore.category.controller.request.CategoryListRequest;
import com.deveyk.bookstore.category.service.command.CategoryChangeStatusCommand;
import com.deveyk.bookstore.category.service.command.CategoryCreateCommand;
import com.deveyk.bookstore.category.service.command.CategoryListCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {

    // CategoryCreateRequest -> CategoryCreateCommand
    CategoryCreateCommand toCreateCommand(CategoryCreateRequest request);

    // CategoryChangeStatusRequest -> CategoryChangeStatusCommand
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "targetStatus", source = "request.targetStatus")
    CategoryChangeStatusCommand toChangeStatusCommand(CategoryChangeStatusRequest request, String categoryId);

    // CategoryListRequest -> CategoryListCommand
    CategoryListCommand toListCommand(CategoryListRequest request);


}
