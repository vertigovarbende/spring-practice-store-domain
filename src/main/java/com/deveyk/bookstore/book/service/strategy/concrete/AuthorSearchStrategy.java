package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookApplicationMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.strategy.AbstractBookSearchStrategy;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSearchStrategy extends AbstractBookSearchStrategy {

    public AuthorSearchStrategy(BookRepository repository, BookApplicationMapper mapper) {
        super(repository, mapper);

    }

    @Override
    public BookSearchType getStrategyType() {
        return BookSearchType.AUTHOR;
    }

    @Override
    protected Specification<BookEntity> buildSpecification(String term) {
        return (root, query, cb) -> {
            query.distinct(true);

            Join<BookEntity, AuthorEntity> author = root.join("authors");

            return cb.or(
                    cb.like(cb.lower(cb.coalesce(author.get("name").get("firstName"), "")), "%" + term + "%"),
                    cb.like(cb.lower(cb.coalesce(author.get("name").get("middleName"), "")), "%" + term + "%"),
                    cb.like(cb.lower(cb.coalesce(author.get("name").get("lastName"), "")), "%" + term + "%"),
                    cb.like(cb.lower(cb.coalesce(author.get("name").get("penName"), "")), "%" + term + "%")
            );
        };
    }

}