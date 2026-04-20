package com.deveyk.bookstore.book.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookUpdateMetadataRequest(
        @NotBlank(message = "{validation.common.not-blank")
        @Size(max = 255, message = "{validation.common.size}")
        String title,
        @PastOrPresent(message = "{validation.common.insert-invalid}")
        LocalDate publicationDate,
        @Size(max = 100, message = "{validation.common.size}")
        String edition,
        @Size(max = 50, message = "{validation.common.size}")
        String language,
        @Positive(message = "{validation.common.insert-invalid}")
        Integer pageCount
) {
}
