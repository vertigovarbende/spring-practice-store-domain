package com.deveyk.bookstore.book.service.impl;


import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.model.mapper.BookEntityToDomainMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import com.deveyk.bookstore.book.service.strategy.context.BookSearchContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookSearchContext bookSearchContext;

    private static final BookEntityToDomainMapper bookEntityToDomainMapper = BookEntityToDomainMapper.INSTANCE;

    @Override
    public List<Book> search(BookSearchRequest request) {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = bookEntityToDomainMapper.mapList(bookEntities);
        SearchStrategy strategy = bookSearchContext.getStrategy(request.searchType());
        return bookSearchContext.executeSearch(strategy, request.searchTerm(), books);
    }


}
