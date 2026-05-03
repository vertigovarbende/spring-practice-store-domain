package com.deveyk.bookstore.category.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryCreateRequest(
        @NotBlank(message = "{validation.common.not-blank}")
        @Size(max = 255, message = "{validation.common.size}")
        String name,
        @Size(max = 255, message = "{validation.common.size}")
        String description
) {
}
