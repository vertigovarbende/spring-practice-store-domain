package com.deveyk.bookstore.book.service.domain;

import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.author.exception.AuthorNotEligibleException;
import com.deveyk.bookstore.author.exception.AuthorDuplicateException;
import com.deveyk.bookstore.book.exception.BookGenreNotEligibleException;
import com.deveyk.bookstore.book.exception.BookStatusAlreadyChangedException;
import com.deveyk.bookstore.book.exception.BookStatusNotSuitableForOperationException;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.deveyk.bookstore.category.exception.CategoryNotEligibleException;
import com.deveyk.bookstore.category.service.domain.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private String id;
    private String title;
    private String isbn10;
    private String isbn13;
    @Builder.Default
    private Set<Author> authors = new HashSet<>();
    private String publisher;
    private LocalDate publicationDate;
    private String edition;
    private String language;
    private Integer pageCount;
    @Builder.Default
    private Set<BookGenre> genres = new HashSet<>();
    private Category category;
    private BookStatus status;

    // Author methods
    /*
        - we can't add the same author twice
        - we can't add the author which is INACTIVE, SUSPENDED or any other status - look at AuthorStatus
     */
    public void addAuthor(Author author) {
        Objects.requireNonNull(author, "author cannot be null");

        validateAuthorEligibility(author);
        validateDuplicateAuthor(author);

        this.authors.add(author);
    }

    public void addAuthor(Set<Author> authors) {
        Objects.requireNonNull(authors, "authors cannot be null");
        authors.forEach(this::addAuthor);
    }

    private void validateAuthorEligibility(Author author) {
        if (!author.isActiveForBookAssignment()) {
            throw new AuthorNotEligibleException(
                    "Author cannot be assigned to the book because status is not ACTIVE or RETIRED"
            );
        }
    }

    private void validateDuplicateAuthor(Author candidate) {
        boolean alreadyExists = this.authors.stream()
                .anyMatch(existing -> existing.sameAuthorAs(candidate));

        if (alreadyExists) {
            throw new AuthorDuplicateException(
                    "Author is already added to this book"
            );
        }
    }

    public void removeAuthor(Author author) {
        Objects.requireNonNull(author, "author cannot be null");
        
        boolean removed = this.authors.removeIf(existing -> existing.sameAuthorAs(author));
        if (!removed) {
            throw new AuthorNotEligibleException(
                    "Author cannot be removed as they are not associated with the book"
            );
        }
    }

    public void removeAuthor(Set<Author> authors) {
        Objects.requireNonNull(authors, "authors cannot be null");
        authors.forEach(this::removeAuthor);
    }

    // Genre methods
    /*
        - we can't add the same genre twice
        - we can't remove the genre which is not associated with the book
     */
    public void addGenre(BookGenre genre) {
        Objects.requireNonNull(genre, "genre cannot be null");

        validateDuplicateGenre(genre);

        this.genres.add(genre);
    }

    public void addGenre(Set<BookGenre> genres) {
        Objects.requireNonNull(genres, "genres cannot be null");
        genres.forEach(this::addGenre);
    }

    private void validateDuplicateGenre(BookGenre candidate) {
        boolean alreadyExists = this.genres.stream()
                .anyMatch(existing -> existing == candidate);

        if (alreadyExists) {
            throw new BookGenreNotEligibleException(
                    "Genre is already added to this book"
            );
        }
    }

    public void removeGenre(BookGenre genre) {
        Objects.requireNonNull(genre, "Genre cannot be null");
        boolean removed = this.genres.remove(genre);
        if (!removed) {
            throw new BookGenreNotEligibleException(
                    "Genre cannot be removed as it is not associated with the book"
            );
        }
    }

    public void removeGenre(Set<BookGenre> genres) {
        Objects.requireNonNull(genres, "genres cannot be null");
        genres.forEach(this::removeGenre);
    }

    // Status methods
    /*
        - only UNAVAILABLE books can be marked as DELETED
        - only DELETED books can be restored
        - book status cannot be changed from AVAILABLE to DELETED
        - book status cannot be changed from DELETED to AVAILABLE
     */
    public void markAsDeleted() {
        if (this.status == BookStatus.DELETED)
            throw new BookStatusAlreadyChangedException(
                    "Book is already in DELETED status"
            );

        if (this.status != BookStatus.UNAVAILABLE)
            throw new BookStatusNotSuitableForOperationException(
                    "Only UNAVAILABLE books can be marked as DELETED"
            );
        this.status = BookStatus.DELETED;
    }

    public void validateHardDeleteAllowed() {
        if (this.status != BookStatus.DELETED) {
            throw new BookStatusNotSuitableForOperationException(
                    "Only DELETED books can be permanently deleted"
            );
        }
    }

    public void restore() {
        if (this.status != BookStatus.DELETED)
            throw new BookStatusNotSuitableForOperationException(
                    "Only DELETED books can be restored"
            );
        this.status = BookStatus.UNAVAILABLE;
    }

    public void changeStatus(BookStatus targetStatus) {
        Objects.requireNonNull(targetStatus, "Target status cannot be null");
        if (this.status == targetStatus)
            throw new BookStatusAlreadyChangedException(
                    "Book is already in " + targetStatus.name() + " status"
            );

        if (!isChangeStatusTransitionAllowed(targetStatus))
            throw new BookStatusNotSuitableForOperationException(
                    "Book status cannot be changed from " + this.status.name() + " to " + targetStatus.name()
            );
        this.status = targetStatus;
    }

    private boolean isChangeStatusTransitionAllowed(BookStatus targetStatus) {
        return this.status.canTransitionTo(targetStatus);
    }

    // Category methods
    public void assignCategory(Category category) {
        Objects.requireNonNull(category, "Category cannot be null");
        if (!category.isActiveForBookAssignment()) {
            throw new CategoryNotEligibleException(
                    "Category cannot be assigned to the book because status is not ACTIVE"
            );
        }
        this.category = category;
    }

    public void removeCategory() {
        if (this.category == null) {
            throw new CategoryNotEligibleException(
                    "Category cannot be removed as it is not associated with the book"
            );
        }
        // we might create a new category instance to represent the removal instead of null
        this.category = null;
    }

    // Update methods
    /*
        - only 'UNAVAILABLE' books can be updated
        - 'DELETED' books cannot be updated
        - 'AVAILABLE' books cannot be updated
     */
    public void updateMetadata(BookMetadata metadata) {
        Objects.requireNonNull(metadata, "Book metadata cannot be null");

        validateMetadataUpdatable();
        applyMetadata(metadata);
    }

    private void validateMetadataUpdatable() {
        if (this.status != BookStatus.UNAVAILABLE) {
            throw new BookStatusNotSuitableForOperationException(
                    "Book metadata can only be updated when status is UNAVAILABLE"
            );
        }
    }

    private void applyMetadata(BookMetadata metadata) {
        this.title = metadata.title();
        this.publicationDate = metadata.publicationDate();
        this.edition = metadata.edition();
        this.language = metadata.language();
        this.pageCount = metadata.pageCount();
    }

}