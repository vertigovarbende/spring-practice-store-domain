package com.deveyk.bookstore.book.service;

import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.service.domain.Book;

import java.util.List;

public interface IBookService {

    public List<Book> search(BookSearchRequest request);

}
