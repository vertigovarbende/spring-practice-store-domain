package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookEntityToDomainMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorSearchStrategy implements SearchStrategy {

    private final BookRepository bookRepository;
    private static final BookEntityToDomainMapper BOOK_ENTITY_TO_DOMAIN_MAPPER = BookEntityToDomainMapper.INSTANCE;

    @Override
    public Page<Book> search(BookSearchCommand command) {
        if (command == null || command.getFilter() == null) {
            return Page.empty(command != null ? command.toPageable() : Pageable.unpaged());
        }

        String searchTerm = command.getFilter().getSearchTerm();

        if (searchTerm == null || searchTerm.isBlank()) {
            return Page.empty(command.toPageable());
        }

        return bookRepository
                .searchByAuthorName(searchTerm.trim(), command.toPageable())
                .map(BOOK_ENTITY_TO_DOMAIN_MAPPER::map);
    }

    @Override
    public BookSearchType getStrategyName() {
        return BookSearchType.AUTHOR;
    }

}