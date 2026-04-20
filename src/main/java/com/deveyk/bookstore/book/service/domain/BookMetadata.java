package com.deveyk.bookstore.book.service.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookMetadata(
        String title,
        LocalDate publicationDate,
        String edition,
        String language,
        Integer pageCount
) {
}