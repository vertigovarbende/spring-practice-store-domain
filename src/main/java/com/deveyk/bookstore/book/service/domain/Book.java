package com.deveyk.bookstore.book.service.domain;

import com.deveyk.bookstore.author.service.domain.Author;
import com.deveyk.bookstore.author.exception.AuthorNotEligibleException;
import com.deveyk.bookstore.author.exception.AuthorDuplicateException;
import com.deveyk.bookstore.book.exception.BookGenreNotEligibleException;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.enums.BookStatus;
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
    private String category;
    private BookStatus status;

    // Helper Methods

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

    /*
        - we can't add the same genre twice
        - we can't add the genre which is not found
     */
    public void addGenre(BookGenre genre) {
        Objects.requireNonNull(genre, "Genre cannot be null");

        if (!this.genres.add(genre)) {
            throw new BookGenreNotEligibleException(
                    "Book already contains genre: " + genre.name()
            );
        }
    }

    public void addGenre(Set<BookGenre> genres) {
        Objects.requireNonNull(genres, "Genres cannot be null");
        genres.forEach(this::addGenre);
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

    public boolean isAvailable() {
        return this.status == BookStatus.AVAILABLE;
    }

    public boolean isOutOfStock() {
        return this.status == BookStatus.OUT_OF_STOCK;
    }

    public boolean hasValidIsbn10() {
        return this.isbn10 != null && this.isbn10.length() == 10;
    }

    public boolean hasValidIsbn13() {
        return this.isbn13 != null && this.isbn13.length() == 13;
    }

    public boolean hasIsbn() {
        return hasValidIsbn10() || hasValidIsbn13();
    }

}