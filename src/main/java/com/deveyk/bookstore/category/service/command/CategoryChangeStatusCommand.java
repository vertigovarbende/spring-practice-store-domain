package com.deveyk.bookstore.category.service.command;

import com.deveyk.bookstore.category.model.enums.CategoryStatus;

public record CategoryChangeStatusCommand(
        String categoryId,
        CategoryStatus targetStatus
) {
}
