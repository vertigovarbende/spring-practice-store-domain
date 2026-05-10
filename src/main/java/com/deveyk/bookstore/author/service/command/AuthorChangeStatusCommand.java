package com.deveyk.bookstore.author.service.command;

import com.deveyk.bookstore.author.model.enums.AuthorStatus;

public record AuthorChangeStatusCommand(
        String authorId,
        AuthorStatus targetStatus
) {
}
