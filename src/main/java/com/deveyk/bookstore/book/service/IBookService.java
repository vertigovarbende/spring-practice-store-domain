package com.deveyk.bookstore.book.service;

import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.service.command.*;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.model.BsPage;

public interface IBookService {

    // List<Book> search(BookSearchRequest request);

    BsPage<Book> search(BookSearchCommand command);

    void create(BookCreateCommand command);

    void delete(String bookId);

    void restore(String bookId);

    void changeStatus(BookChangeStatusCommand command);

    void addAuthorToBook(BookAddAuthorCommand command);

    void addGenreToBook(BookAddGenreCommand command);

    void removeAuthorFromBook(BookRemoveAuthorCommand command);

    void removeGenreFromBook(BookRemoveGenreCommand command);

}
