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

@Component
@RequiredArgsConstructor
public class BookSearchContext {

    private final List<SearchStrategy> searchStrategies;
    private final Map<BookSearchType, SearchStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (SearchStrategy strategy : searchStrategies) {
            strategyMap.put(strategy.getStrategyName(), strategy);
        }
    }

    // refactor strategy retrieval to use map lookup
    public Page<Book> executeSearch(BookSearchCommand command) {
        if (command == null || command.getFilter() == null) {
            throw new StrategyNotFoundException("Search command or filter cannot be null");
        }

        BookSearchType searchType = BookSearchType.from(command.getFilter().getSearchType());
        SearchStrategy strategy = getStrategy(searchType);

        if (strategy == null) {
            throw new StrategyNotFoundException("Search strategy not found for type: " + searchType);
        }

        return strategy.search(command);
    }


    public SearchStrategy getStrategy(BookSearchType strategyName) {
        if (strategyName == null) {
            throw new StrategyNotFoundException("Search type cannot be null");
        }

        SearchStrategy strategy = strategyMap.get(strategyName);

        if (strategy == null) {
            throw new StrategyNotFoundException("Search strategy not found for type: " + strategyName);
        }

        return strategy;
    }

}
