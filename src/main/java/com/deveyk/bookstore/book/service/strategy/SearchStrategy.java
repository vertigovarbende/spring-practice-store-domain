package com.deveyk.bookstore.book.service.strategy;

import com.deveyk.bookstore.book.service.domain.Book;

import java.util.List;

public interface SearchStrategy {

    List<Book> search(String searchTerm, List<Book> books);

    String getStrategyName();

}
