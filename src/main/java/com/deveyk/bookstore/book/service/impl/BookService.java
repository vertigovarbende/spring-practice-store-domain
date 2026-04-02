package com.deveyk.bookstore.book.service.impl;


import com.deveyk.bookstore.author.exception.AuthorNotFoundException;
import com.deveyk.bookstore.author.model.mapper.AuthorEntityToDomainMapper;
import com.deveyk.bookstore.author.repository.AuthorRepository;
import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.book.controller.request.BookAddAuthorRequest;
import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.controller.request.BookSearchRequest;
import com.deveyk.bookstore.book.exception.BookNotFoundException;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.mapper.BookDomainToEntityMapper;
import com.deveyk.bookstore.book.model.mapper.BookEntityToDomainMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import com.deveyk.bookstore.book.service.strategy.context.BookSearchContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * i am gonna refactor all methods in this class. i am just trying some stuff
 * bsc some of them could create some problem issue - especially addGenre and addAuthor - map
 */
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookSearchContext bookSearchContext;

    private static final BookEntityToDomainMapper bookEntityToDomainMapper = BookEntityToDomainMapper.INSTANCE;
    private static final BookDomainToEntityMapper bookDomainToEntityMapper = BookDomainToEntityMapper.INSTANCE;
    private static final AuthorEntityToDomainMapper authorEntityToDomainMapper = AuthorEntityToDomainMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public List<Book> search(BookSearchRequest request) {
        List<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = bookEntityToDomainMapper.mapList(bookEntities);
        SearchStrategy strategy = bookSearchContext.getStrategy(request.searchType());
        return bookSearchContext.executeSearch(strategy, request.searchTerm(), books);
    }

    @Override
    @Transactional
    public void addAuthorToBook(String bookId, BookAddAuthorRequest request) {

        AuthorEntity authorEntity = this.returnExistingOfAuthor(request.authorId());
        Author author = authorEntityToDomainMapper.map(authorEntity);

        BookEntity bookEntity = this.returnExistingOfBook(bookId);
        Book book = bookEntityToDomainMapper.map(bookEntity);

        book.addAuthor(author);

        BookEntity updatedBookEntity = bookDomainToEntityMapper.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeAuthorFromBook(String bookId, String authorId) {
        BookEntity bookEntity = this.returnExistingOfBook(bookId);
        Book book = bookEntityToDomainMapper.map(bookEntity);

        AuthorEntity authorEntity = this.returnExistingOfAuthor(authorId);
        Author author = authorEntityToDomainMapper.map(authorEntity);

        book.removeAuthor(author);

        BookEntity updatedBookEntity = bookDomainToEntityMapper.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addGenreToBook(String bookId, BookAddGenreRequest request) {
        BookEntity bookEntity = this.returnExistingOfBook(bookId);
        Book book = bookEntityToDomainMapper.map(bookEntity);

        BookGenre bookGenre = BookGenre.from(request.genre());

        book.addGenre(bookGenre);

        BookEntity updatedBookEntity = bookDomainToEntityMapper.map(book);
        bookRepository.save(updatedBookEntity);
    }

    // i am gonna change the parameter - BookAddGenreRequest request
    @Override
    @Transactional
    public void removeGenreFromBook(String bookId, BookAddGenreRequest request) {
        BookEntity bookEntity = this.returnExistingOfBook(bookId);
        Book book = bookEntityToDomainMapper.map(bookEntity);

        BookGenre bookGenre = BookGenre.from(request.genre());

        book.removeGenre(bookGenre);

        BookEntity updatedBookEntity = bookDomainToEntityMapper.map(book);
        bookRepository.save(updatedBookEntity);
    }

    private BookEntity returnExistingOfBook(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    private AuthorEntity returnExistingOfAuthor(String authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + authorId));
    }


}
