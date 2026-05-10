package com.deveyk.bookstore.author.service.command;

import com.deveyk.bookstore.author.service.domain.AuthorContact;
import com.deveyk.bookstore.author.service.domain.AuthorName;

public record AuthorCreateCommand(
        AuthorName name,
        AuthorContact contact
) {

}
