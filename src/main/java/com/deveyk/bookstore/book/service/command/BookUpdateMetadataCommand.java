package com.deveyk.bookstore.book.service.command;

import java.time.LocalDate;

public record BookUpdateMetadataCommand(
        String bookId,
        String title,
        LocalDate publicationDate,
        String edition,
        String language,
        Integer pageCount
) {
}
