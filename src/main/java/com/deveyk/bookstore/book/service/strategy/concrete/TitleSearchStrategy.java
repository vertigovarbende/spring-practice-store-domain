package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TitleSearchStrategy implements SearchStrategy {

    @Override
    public List<Book> search(String searchTerm, List<Book> books) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getStrategyName() {
        return "TITLE";
    }


}
