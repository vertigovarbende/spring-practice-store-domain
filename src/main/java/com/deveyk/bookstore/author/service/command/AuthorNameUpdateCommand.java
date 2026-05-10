package com.deveyk.bookstore.author.service.command;

import com.deveyk.bookstore.author.service.domain.AuthorName;

public record AuthorNameUpdateCommand(
        String authorId,
        AuthorName name
) {
}
