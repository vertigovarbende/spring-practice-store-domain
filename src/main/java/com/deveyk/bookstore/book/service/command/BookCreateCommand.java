package com.deveyk.bookstore.book.service.command;

import com.deveyk.bookstore.book.model.enums.BookStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record BookCreateCommand(
        String title,
        BookStatus status,
        String publisher,
        LocalDate publicationDate,
        String edition,
        String language,
        Integer pageCount,
        UUID categoryId,
        Set<UUID> authorIds,
        Set<String> genres
) {


}
