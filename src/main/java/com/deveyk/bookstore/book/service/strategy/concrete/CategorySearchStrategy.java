package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookApplicationMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.strategy.AbstractBookSearchStrategy;
import com.deveyk.bookstore.category.repository.entity.CategoryEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySearchStrategy extends AbstractBookSearchStrategy {

    public CategorySearchStrategy(BookRepository repository, BookApplicationMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<BookEntity> buildSpecification(String term) {
        return (root, query, cb) -> {
            Join<BookEntity, CategoryEntity> categoryJoin = root.join("category", JoinType.LEFT);

            return cb.like(
                    cb.lower(cb.coalesce(categoryJoin.get("name"), "")),
                    "%" + term.toLowerCase() + "%"
            );
        };
    }

    @Override
    public BookSearchType getStrategyType() {
        return BookSearchType.CATEGORY;
    }
}


