package com.deveyk.bookstore.book.service.command;

import java.util.Set;
import java.util.UUID;

public record BookAddAuthorCommand(
        String bookId,
        Set<UUID> authorIds
) {
}
