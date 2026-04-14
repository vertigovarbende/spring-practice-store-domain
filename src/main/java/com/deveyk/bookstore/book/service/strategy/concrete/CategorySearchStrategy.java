package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookApplicationMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.strategy.AbstractBookSearchStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySearchStrategy extends AbstractBookSearchStrategy {

    public CategorySearchStrategy(BookRepository repository, BookApplicationMapper mapper) {
        super(repository, mapper);
    }

    // i am gonna change it when i create entity for category domain
    @Override
    protected Specification<BookEntity> buildSpecification(String term) {
        return (root, query, cb) ->
                cb.like(cb.lower(cb.coalesce(root.get("category"), "")), "%" + term + "%");
    }

    @Override
    public BookSearchType getStrategyType() {
        return BookSearchType.CATEGORY;
    }
}


