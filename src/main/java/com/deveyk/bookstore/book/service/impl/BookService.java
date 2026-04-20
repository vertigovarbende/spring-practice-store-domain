package com.deveyk.bookstore.book.service.impl;

import com.deveyk.bookstore.author.exception.AuthorNotFoundException;
import com.deveyk.bookstore.author.model.mapper.AuthorApplicationMapper;
import com.deveyk.bookstore.author.repository.AuthorRepository;
import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.book.exception.BookNotFoundException;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.enums.BookSearchType;
import com.deveyk.bookstore.book.model.mapper.*;
import com.deveyk.bookstore.book.repository.BookRepository;
import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.IBookService;
import com.deveyk.bookstore.book.service.command.*;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.book.service.domain.BookMetadata;
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
    // Mappers
    private final BookApplicationMapper bookApplicationMapper;
    private final AuthorApplicationMapper authorApplicationMapper;

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
        Book book = bookApplicationMapper.toDomain(command);
        if (command.authorIds() != null && !command.authorIds().isEmpty()) {
            /*
            Set<Author> authors = command.authorIds().stream()
                    .map(authorsId -> getAuthorEntity(authorsId.toString()))
                    .map(authorApplicationMapper::toDomain)
                    .collect(Collectors.toSet());
             */
            Set<Author> authors2 = this.getAuthorEntity(command.authorIds()).stream()
                            .map(authorApplicationMapper::toDomain)
                            .collect(Collectors.toSet());
            book.addAuthor(authors2);
        }
        if (command.genres() != null && !command.genres().isEmpty()) {
            Set<BookGenre> genres = command.genres().stream()
                    .map(BookGenre::from)
                    .collect(Collectors.toSet());
            book.addGenre(genres);
        }

        BookEntity bookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(bookEntity);
    }

    @Override
    @Transactional
    public void updateMetadata(BookUpdateMetadataCommand command) {
        Objects.requireNonNull(command, "BookUpdateMetadataCommand cannot be null");

        BookEntity bookEntity = getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        BookMetadata metadata = BookMetadata.builder()
                .title(command.title())
                .publicationDate(command.publicationDate())
                .edition(command.edition())
                .language(command.language())
                .pageCount(command.pageCount())
                .build();

        book.updateMetadata(metadata);
        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void delete(String bookId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = bookApplicationMapper.toDomain(bookEntity);
        book.markAsDeleted();
        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    // refactor this method - only DELETED books should be deleted permanently
    @Override
    @Transactional
    public void hardDelete(String bookId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = bookApplicationMapper.toDomain(bookEntity);

        book.validateHardDeleteAllowed();

        bookRepository.delete(bookEntity);
    }

    @Transactional
    public void restore(String bookId) {
        BookEntity bookEntity = this.getBookEntity(bookId);
        Book book = bookApplicationMapper.toDomain(bookEntity);
        book.restore();
        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void changeStatus(BookChangeStatusCommand command) {
        Objects.requireNonNull(command, "BookChangeStatusCommand cannot be null");

        BookEntity bookEntity = getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        book.changeStatus(command.targetStatus());

        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addAuthorToBook(BookAddAuthorCommand command) {
        Objects.requireNonNull(command, "BookAddAuthorCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        Set<Author> authors = this.getAuthorEntity(command.authorIds()).stream()
                .map(authorApplicationMapper::toDomain)
                .collect(Collectors.toSet());

        book.addAuthor(authors);

        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeAuthorFromBook(BookRemoveAuthorCommand command) {
        Objects.requireNonNull(command, "BookRemoveAuthorCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        Set<Author> authors = this.getAuthorEntity(command.authorIds()).stream()
                .map(authorApplicationMapper::toDomain)
                .collect(Collectors.toSet());

        book.removeAuthor(authors);

        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void addGenreToBook(BookAddGenreCommand command) {
        Objects.requireNonNull(command, "BookAddGenreCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        Set<BookGenre> genres = command.genres().stream()
                .map(BookGenre::from)
                .collect(Collectors.toSet());

        book.addGenre(genres);

        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
        bookRepository.save(updatedBookEntity);
    }

    @Override
    @Transactional
    public void removeGenreFromBook(BookRemoveGenreCommand command) {
        Objects.requireNonNull(command, "BookRemoveGenreCommand cannot be null");

        BookEntity bookEntity = this.getBookEntity(command.bookId());
        Book book = bookApplicationMapper.toDomain(bookEntity);

        Set<BookGenre> genres = command.genres().stream()
                .map(BookGenre::from)
                .collect(Collectors.toSet());

        book.removeGenre(genres);

        BookEntity updatedBookEntity = bookApplicationMapper.toEntity(book);
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
