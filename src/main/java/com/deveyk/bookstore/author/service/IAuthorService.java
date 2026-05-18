package com.deveyk.bookstore.author.service;

import com.deveyk.bookstore.author.service.command.*;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.common.model.BsPage;

public interface IAuthorService {

    void create(AuthorCreateCommand command);

    BsPage<Author> list(AuthorListCommand command);

    Author getById(String authorId);

    void updateName(AuthorNameUpdateCommand command);

    void updateContact(AuthorContactUpdateCommand command);

    void changeStatus(AuthorChangeStatusCommand command);

    void verify(String authorId);

    void unverify(String authorId);



}
