package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AuthorSearchStrategy implements SearchStrategy {

    // idk ?????
    @Override
    public List<Book> search(String searchTerm, List<Book> books) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return List.of();
        }
        String normalizedSearchTerm = searchTerm.toLowerCase();

        return books.stream()
                .filter(book -> book.getAuthors().stream()
                        .anyMatch(author -> {
                            var name = author.getName();
                            if (name == null) return false;
                            return Stream.of(name.getFirstName(), name.getMiddleName(), name.getLastName(), name.getPenName())
                                    .filter(Objects::nonNull)
                                    .map(String::toLowerCase)
                                    .anyMatch(s -> s.contains(normalizedSearchTerm));
                        }))
                .collect(Collectors.toList());
    }

    @Override
    public String getStrategyName() {
        return "AUTHOR";
    }

}