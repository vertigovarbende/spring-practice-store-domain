package com.deveyk.bookstore.book.service.command;

import com.deveyk.bookstore.book.model.enums.BookStatus;

public record BookChangeStatusCommand(
        String bookId,
        BookStatus targetStatus
) {
    // is it necessary to validate bookId??
    public BookChangeStatusCommand {
        if (bookId == null || bookId.isBlank()) {
            throw new IllegalArgumentException("BookId cannot be null or blank");
        }
    }

}
