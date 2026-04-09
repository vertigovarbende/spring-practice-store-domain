package com.deveyk.bookstore.book.service.strategy.concrete;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.BookEntityToDomainMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.strategy.AbstractBookSearchStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSearchStrategy extends AbstractBookSearchStrategy {

    public TitleSearchStrategy(BookRepository repository, BookEntityToDomainMapper bookEntityToDomainMapper) {
        super(repository, bookEntityToDomainMapper);
    }

    @Override
    public BookSearchType getStrategyType() {
        return BookSearchType.TITLE;
    }

    @Override
    protected Specification<BookEntity> buildSpecification(String term) {
        return (root, query, cb) ->
                cb.like(cb.lower(cb.coalesce(root.get("title"), "")), "%" + term + "%");
    }

}
