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
import com.deveyk.bookstore.book.model.mapper.*;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.command.*;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.strategy.SearchStrategy;
import com.deveyk.bookstore.book.service.strategy.context.BookSearchContext;
import com.deveyk.bookstore.common.model.BsPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private static final BookCreateCommandToDomainMapper BOOK_CREATE_COMMAND_TO_DOMAIN_MAPPER = BookCreateCommandToDomainMapper.INSTANCE;
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

    @Override
    @Transactional
    public void create(BookCreateCommand command) {
        Objects.requireNonNull(command, "BookCreateCommand cannot be null");
        Book book = BOOK_CREATE_COMMAND_TO_DOMAIN_MAPPER.map(command);
        if (command.authorIds() != null && !command.authorIds().isEmpty()) {
            /*
            Set<Author> authors = command.authorIds().stream()
                    .map(authorsId -> getAuthorEntity(authorsId.toString()))
                    .map(AUTHOR_ENTITY_TO_DOMAIN_MAPPER::map)
                    .collect(Collectors.toSet());
             */
            Set<Author> authors2 = this.getAuthorEntity(command.authorIds()).stream()
                            .map(AUTHOR_ENTITY_TO_DOMAIN_MAPPER::map)
                            .collect(Collectors.toSet());
            book.addAuthor(authors2);
        }
        if (command.genres() != null && !command.genres().isEmpty()) {
            Set<BookGenre> genres = command.genres().stream()
                    .map(BookGenre::from)
                    .collect(Collectors.toSet());
            book.addGenre(genres);
        }

        BookEntity bookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(bookEntity);
    }

    @Override
    @Transactional
    public void delete(String bookId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);
        book.markAsDeleted();
        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Transactional
    public void restore(String bookId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);
        book.restore();
        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void changeStatus(BookChangeStatusCommand command) {
        Objects.requireNonNull(command, "BookChangeStatusCommand cannot be null");

        BookEntity bookEntity = getBookEntity(command.bookId());
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        book.changeStatus(command.targetStatus());

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addAuthorToBook(BookAddAuthorCommand command) {
        Objects.requireNonNull(command, "BookAddAuthorCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        Set<Author> authors = this.getAuthorEntity(command.authorIds()).stream()
                .map(AUTHOR_ENTITY_TO_DOMAIN_MAPPER::map)
                .collect(Collectors.toSet());

        book.addAuthor(authors);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeAuthorFromBook(BookRemoveAuthorCommand command) {
        Objects.requireNonNull(command, "BookRemoveAuthorCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        Set<Author> authors = this.getAuthorEntity(command.authorIds()).stream()
                .map(AUTHOR_ENTITY_TO_DOMAIN_MAPPER::map)
                .collect(Collectors.toSet());

        book.removeAuthor(authors);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addGenreToBook(BookAddGenreCommand command) {
        Objects.requireNonNull(command, "BookAddGenreCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        Set<BookGenre> genres = command.genres().stream()
                .map(BookGenre::from)
                .collect(Collectors.toSet());

        book.addGenre(genres);

        BookEntity updatedBookEntity = BOOK_DOMAIN_TO_ENTITY_MAPPER.map(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeGenreFromBook(BookRemoveGenreCommand command) {
        Objects.requireNonNull(command, "BookRemoveGenreCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = BOOK_ENTITY_TO_DOMAIN_MAPPER.map(bookEntity);

        Set<BookGenre> genres = command.genres().stream()
                .map(BookGenre::from)
                .collect(Collectors.toSet());

        book.removeGenre(genres);

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

    private Set<AuthorEntity> getAuthorEntity(Set<UUID> authorIds) {
        List<AuthorEntity> authorEntities = authorRepository.findAllById(authorIds.stream()
                .map(UUID::toString)
                .collect(Collectors.toList()));

        if (authorEntities.size() != authorIds.size()) {
            throw new AuthorNotFoundException("One or more authors were not found");
        }

        return authorEntities.stream()
                .collect(Collectors.toSet());
    }

}
