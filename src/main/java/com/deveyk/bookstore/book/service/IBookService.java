package com.deveyk.bookstore.book.service;

import com.deveyk.bookstore.book.controller.request.BookAddAuthorRequest;
import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.service.domain.Book;

import java.util.List;

public interface IBookService {

    List<Book> search(BookSearchRequest request);

    void addAuthorToBook(String bookId, BookAddAuthorRequest request);

    void removeAuthorFromBook(String bookId, String authorId);

    void addGenreToBook(String bookId, BookAddGenreRequest request);

    void removeGenreFromBook(String bookId, BookAddGenreRequest request);

}
