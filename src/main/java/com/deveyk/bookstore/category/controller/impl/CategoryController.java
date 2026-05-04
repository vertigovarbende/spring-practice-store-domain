package com.deveyk.bookstore.category.controller.impl;

import com.deveyk.bookstore.category.controller.mapper.CategoryRequestMapper;
import com.deveyk.bookstore.category.controller.mapper.CategoryResponseMapper;
import com.deveyk.bookstore.category.controller.request.CategoryChangeStatusRequest;
import com.deveyk.bookstore.category.controller.request.CategoryCreateRequest;
import com.deveyk.bookstore.category.controller.request.CategoryListRequest;
import com.deveyk.bookstore.category.controller.response.CategoryResponse;
import com.deveyk.bookstore.category.service.ICategoryService;
import com.deveyk.bookstore.category.service.command.CategoryChangeStatusCommand;
import com.deveyk.bookstore.category.service.command.CategoryCreateCommand;
import com.deveyk.bookstore.category.service.domain.Category;
import com.deveyk.bookstore.common.controller.response.BaseResponse;
import com.deveyk.bookstore.common.controller.response.BsPageResponse;
import com.deveyk.bookstore.common.model.BsPage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    // POST - /api/v1/categories - create()
    @PostMapping
    public BaseResponse<Void> create(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryCreateCommand command = categoryRequestMapper.toCreateCommand(request);
        categoryService.create(command);
        return BaseResponse.successOf(HttpStatus.CREATED, "Category created successfully");
    }

    // GET - /api/v1/categories/{categoryId} - get()
    @GetMapping("/{categoryId}")
    public BaseResponse<CategoryResponse> get(@PathVariable String categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        CategoryResponse response = categoryResponseMapper.toResponse(category);
        return BaseResponse.successOf(response);
    }

    // GET - /api/v1/categories - list()
    @GetMapping
    public BaseResponse<BsPageResponse<CategoryResponse>> list(@Valid @RequestBody CategoryListRequest request) {
        BsPage<Category> categoryPage = categoryService.list(
                categoryRequestMapper.toListCommand(request));

        BsPageResponse<CategoryResponse> response = BsPageResponse.<CategoryResponse>builder()
                .content(categoryPage.getContent()
                        .stream()
                        .map(categoryResponseMapper::toResponse)
                        .toList())
                .page(categoryPage)
                .build();
        return BaseResponse.successOf(response);
    }

    // PATCH - /api/v1/categories/{categoryId}/status
    @PatchMapping("/{categoryId}/status")
    public BaseResponse<Void> changeStatus(
            @PathVariable
            @NotBlank(message = "{validation.common.not-blank}") String categoryId,
            @Valid @RequestBody CategoryChangeStatusRequest request
    ) {
        CategoryChangeStatusCommand command = categoryRequestMapper.toChangeStatusCommand(request, categoryId);
        categoryService.changeStatus(command);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Category status changed successfully");
    }

    // DELETE - /api/v1/categories/{categoryId}
    @DeleteMapping("/{categoryId}")
    public BaseResponse<Void> delete(@PathVariable @NotBlank(message = "{validation.common.not-blank}") String categoryId) {
        categoryService.delete(categoryId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Category deleted successfully");
    }

    // PATCH - /api/v1/categories/{categoryId}/restore
    @PatchMapping("/{categoryId}/restore")
    public BaseResponse<Void> restore(@PathVariable @NotBlank(message = "{validation.common.not-blank}") String categoryId) {
        categoryService.restore(categoryId);
        return BaseResponse.successOf(HttpStatus.NO_CONTENT, "Category restored successfully");
    }

}
