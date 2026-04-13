package com.deveyk.bookstore.book.service.command;

import java.util.Set;

public record BookRemoveGenreCommand(
        String bookId,
        Set<String> genres
) {

}
