package com.deveyk.bookstore.book.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record BookAddAuthorRequest(
        @NotEmpty(message = "{validation.common.not-empty}")
        @Size(max = 10, message = "{validation.common.size}")
        Set<UUID> authorIds
) {

}
