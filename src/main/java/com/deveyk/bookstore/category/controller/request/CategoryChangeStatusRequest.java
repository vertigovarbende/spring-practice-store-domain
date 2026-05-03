package com.deveyk.bookstore.category.controller.request;

import com.deveyk.bookstore.category.model.enums.CategoryStatus;
import jakarta.validation.constraints.NotNull;

public record CategoryChangeStatusRequest(
        @NotNull(message = "{validation.common.not-null}")
        CategoryStatus targetStatus
) {
}
