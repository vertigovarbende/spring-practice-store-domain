package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookApplicationMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.strategy.AbstractBookSearchStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class GenreSearchStrategy extends AbstractBookSearchStrategy {

    public GenreSearchStrategy(BookRepository repository, BookApplicationMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<BookEntity> buildSpecification(String term) {
        return (root, query, cb) -> {
            query.distinct(true);
            return cb.like(
                    cb.lower(cb.function("str", String.class, root.join("genres"))),
                    "%" + term.toLowerCase() + "%"
            );
        };
    }

    @Override
    public BookSearchType getStrategyType() {
        return BookSearchType.GENRE;
    }
}
