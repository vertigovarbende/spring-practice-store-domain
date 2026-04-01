package com.deveyk.bookstore.book.repository.entity;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.deveyk.bookstore.common.repository.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "BS_BOOKS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(
        name = "Book.withAuthors",
        attributeNodes = {
                @NamedAttributeNode("authors")
        }
) // Should I use JOIN FETCH?
public class BookEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "TITLE", nullable = false, length = 500)
    private String title;

    @Column(name = "ISBN_10", length = 10, unique = true)
    private String isbn10;

    @Column(name = "ISBN_13", length = 13, unique = true)
    private String isbn13;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "BS_BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private Set<AuthorEntity> authors;

    @Column(name = "PUBLISHER", length = 200)
    private String publisher;

    @Column(name = "PUBLICATION_DATE", nullable = true)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate publicationDate;

    @Column(name = "EDITION")
    private String edition;

    @Column(name = "LANGUAGE", length = 50)
    private String language;

    @Column(name = "PAGE_COUNT")
    private Integer pageCount;

    @ElementCollection(targetClass = BookGenre.class, fetch = FetchType.LAZY)
    @CollectionTable(
            name = "BS_BOOK_GENRES",
            joinColumns = @JoinColumn(name = "BOOK_ID")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE", nullable = false)
    private Set<BookGenre> genres;

    @Column(name = "CATEGORY", length = 100)
    private String category;        // i am gonna create CategoryEntity

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    // Methods
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity bookEntity)) return false;
        return id != null && id.equals(bookEntity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}