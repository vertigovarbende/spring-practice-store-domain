package com.deveyk.bookstore.book.service.strategy.context;

import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import com.deveyk.bookstore.common.exception.StrategyNotFoundException;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookSearchContext {

    private final List<SearchStrategy> strategies;
    private Map<BookSearchType, SearchStrategy> strategyMap;

    @PostConstruct
    public void init() {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        SearchStrategy::getStrategyType,
                        Function.identity()
                ));
    }

    public SearchStrategy getStrategy(BookSearchType type) {
        SearchStrategy strategy = strategyMap.get(type);

        if (strategy == null) {
            throw new StrategyNotFoundException("Strategy not found: " + type);
        }

        return strategy;
    }

}
