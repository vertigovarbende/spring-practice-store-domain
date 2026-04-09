package com.deveyk.bookstore.book.service.strategy;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import org.springframework.data.domain.Page;

public interface SearchStrategy {

    Page<Book> search(BookSearchCommand command);

    BookSearchType getStrategyType();

}
