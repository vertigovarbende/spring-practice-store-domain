package com.deveyk.bookstore.book.service.strategy;

import com.deveyk.bookstore.book.model.mapper.BookApplicationMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public abstract class AbstractBookSearchStrategy implements SearchStrategy {

    protected final BookRepository repository;
    protected final BookApplicationMapper bookApplicationMapper;

    @Override
    public Page<Book> search(BookSearchCommand command) {
        if (command == null || command.getFilter() == null) {
            return Page.empty();
        }

        String term = command.getFilter().getSearchTerm();
        if (term == null || term.isBlank()) {
            return Page.empty(command.toPageable());
        }

        // common filters (from command)
        Specification<BookEntity> commonSpec = command.toSpecification(BookEntity.class);
        // specific search (from strategy)
        Specification<BookEntity> specificSpec = buildSpecification(term.toLowerCase());
        // final specification
        Specification<BookEntity> finalSpec = commonSpec.and(specificSpec);

        return repository.findAll(finalSpec, command.toPageable())
                .map(bookApplicationMapper::toDomain);
    }

    protected abstract Specification<BookEntity> buildSpecification(String term);

}
