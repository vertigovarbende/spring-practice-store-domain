package com.deveyk.bookstore.book.service;

import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.service.command.AddAuthorToBookCommand;
import com.deveyk.bookstore.book.service.command.BookCreateCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import com.deveyk.bookstore.common.model.BsPage;

public interface IBookService {

    // List<Book> search(BookSearchRequest request);

    BsPage<Book> search(BookSearchCommand command);

    void create(BookCreateCommand command);

    // void addAuthorToBook(String bookId, BookAddAuthorRequest request);

    void addAuthorToBook(String bookId, AddAuthorToBookCommand command);

    void removeAuthorFromBook(String bookId, String authorId);

    void addGenreToBook(String bookId, BookAddGenreRequest request);

    void removeGenreFromBook(String bookId, BookAddGenreRequest request);

}
