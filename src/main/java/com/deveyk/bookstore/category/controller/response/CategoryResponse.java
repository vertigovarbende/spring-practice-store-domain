package com.deveyk.bookstore.category.controller.response;

import java.time.LocalDateTime;

public record CategoryResponse(
        String id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
