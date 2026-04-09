package com.deveyk.bookstore.book.service.impl;


import com.deveyk.bookstore.author.exception.AuthorNotFoundException;
import com.deveyk.bookstore.author.model.mapper.AuthorEntityToDomainMapper;
import com.deveyk.bookstore.author.repository.AuthorRepository;
import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.book.controller.request.BookAddGenreRequest;
import com.deveyk.bookstore.book.exception.BookNotFoundException;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.AddAuthorToBookCommandToDomainMapper;
import com.deveyk.bookstore.book.model.mapper.BookDomainToEntityMapper;
import com.deveyk.bookstore.book.model.mapper.BookEntityToDomainMapper;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.command.AddAuthorToBookCommand;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.command.BookSearchCommand;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import com.deveyk.bookstore.book.service.strategy.context.BookSearchContext;
import com.deveyk.bookstore.common.model.BsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    private static final AddAuthorToBookCommandToDomainMapper ADD_AUTHOR_TO_BOOK_COMMAND_TO_DOMAIN_MAPPER = AddAuthorToBookCommandToDomainMapper.INSTANCE;
    private static final BookEntityToDomainMapper BOOK_ENTITY_TO_DOMAIN_MAPPER = BookEntityToDomainMapper.INSTANCE;
    private static final BookDomainToEntityMapper BOOK_DOMAIN_TO_ENTITY_MAPPER = BookDomainToEntityMapper.INSTANCE;
    private static final AuthorEntityToDomainMapper AUTHOR_ENTITY_TO_DOMAIN_MAPPER = AuthorEntityToDomainMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public BsPage<Book> search(BookSearchCommand command) {
        if (command == null || command.getFilter() == null) {
            return BsPage.<Book>builder()
                    .content(List.of())
                    .build();
        }

        BookSearchType type = command.getFilter().getSearchType();
        SearchStrategy strategy = bookSearchContext.getStrategy(type);

        Page<Book> bookPage = strategy.search(command);

        return BsPage.<Book>builder()
                .content(bookPage.getContent())
                .page(bookPage)
                .filteredBy(command.getFilter())
                .build();
    }


    /*
    @Override
    @Transactional
    public void addAuthorToBook(String bookId, BookAddAuthorRequest request) {

        AuthorEntity authorEntity = this.returnExistingOfAuthor(request.authorId());
        Author author = AUTHOR_ENTITY_TO_DOMAIN_MAPPER.map(authorEntity);

        BookEntity bookEntity = this.returnExistingOfBook(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        book.addAuthor(author);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }
     */


    @Override
    @Transactional
    public void addAuthorToBook(String bookId, AddAuthorToBookCommand command) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        AuthorEntity authorEntity = this.getAuthorEntity(command.authorId());
        Author author = AUTHOR_ENTITY_TO_DOMAIN_MAPPER.map(authorEntity);

        book.addAuthor(author);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeAuthorFromBook(String bookId, String authorId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        AuthorEntity authorEntity = this.getAuthorEntity(authorId);
        Author author = AUTHOR_ENTITY_TO_DOMAIN_MAPPER.map(authorEntity);

        book.removeAuthor(author);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addGenreToBook(String bookId, BookAddGenreRequest request) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        BookGenre bookGenre = BookGenre.from(request.genre());

        book.addGenre(bookGenre);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    // i am gonna change the parameter - BookAddGenreRequest request
    @Override
    @Transactional
    public void removeGenreFromBook(String bookId, BookAddGenreRequest request) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        BookGenre bookGenre = BookGenre.from(request.genre());

        book.removeGenre(bookGenre);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    private BookEntity getBookEntity(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    private AuthorEntity getAuthorEntity(String authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + authorId));
    }


}
