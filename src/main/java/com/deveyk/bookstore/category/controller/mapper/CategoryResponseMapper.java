package com.deveyk.bookstore.category.controller.mapper;

import com.deveyk.bookstore.category.controller.response.CategoryResponse;
import com.deveyk.bookstore.category.service.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    // Category -> CategoryResponse
    CategoryResponse toResponse(Category category);


}
