package com.deveyk.bookstore.book.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record BookRemoveGenreRequest(
        @NotEmpty(message = "{validation.common.not-empty}")
        @Size(max = 10, message = "{validation.common.size}")
        Set<@NotBlank(message = "{validation.common.not-blank}")
            @Size(max = 50, message = "{validation.common.size}") String> genres
) {
}
