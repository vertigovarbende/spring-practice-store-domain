package com.deveyk.bookstore.book.service.command;

import java.util.UUID;

public record BookAssignCategoryCommand(
        String bookId,
        UUID categoryId
) {
}
