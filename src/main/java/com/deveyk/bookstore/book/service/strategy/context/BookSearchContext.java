package com.deveyk.bookstore.book.service.strategy.context;

import com.deveyk.bookstore.book.exception.StrategyNotFoundException;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookSearchContext {

    private final List<SearchStrategy> searchStrategies;

    private Map<String, SearchStrategy> getStrategyMap() {
        return searchStrategies.stream()
                .collect(Collectors.toMap(
                        SearchStrategy::getStrategyName,
                        Function.identity()
                ));
    }

    public List<Book> executeSearch(SearchStrategy searchStrategy, String searchTerm, List<Book> books) {
        return searchStrategy.search(searchTerm, books);
    }

    public SearchStrategy getStrategy(String strategyName) {
        SearchStrategy strategy = getStrategyMap().get(strategyName.toUpperCase());
        if (strategy == null) {
            throw new StrategyNotFoundException("Search type not found : " + strategyName);
        }
        return strategy;
    }

}
