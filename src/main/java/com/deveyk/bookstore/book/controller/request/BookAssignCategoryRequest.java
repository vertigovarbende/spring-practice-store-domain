package com.deveyk.bookstore.book.controller.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record BookAssignCategoryRequest(
        @NotBlank(message = "{validation.common.not-blank}")
        UUID categoryId
) {
}
