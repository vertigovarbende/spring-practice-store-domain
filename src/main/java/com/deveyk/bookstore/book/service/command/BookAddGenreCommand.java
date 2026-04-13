package com.deveyk.bookstore.book.service.command;

import java.util.Set;

public record BookAddGenreCommand(
        String bookId,
        Set<String> genres
) {
}
