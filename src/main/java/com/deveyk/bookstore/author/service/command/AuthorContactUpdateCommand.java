package com.deveyk.bookstore.author.service.command;

import com.deveyk.bookstore.author.service.domain.AuthorContact;

public record AuthorContactUpdateCommand(
        String authorId,
        AuthorContact contact
) {
}
