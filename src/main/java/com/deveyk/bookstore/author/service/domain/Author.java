package com.deveyk.bookstore.author.service.domain;

import com.deveyk.bookstore.author.model.enums.AuthorStatus;
import com.deveyk.bookstore.book.service.domain.Book;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    private String id;
    private AuthorName name;
    private AuthorContact contact;
    private AuthorStatus status;
    private Boolean isVerified;
   //  private Set<Book> books;

    public boolean isActiveForBookAssignment() {
        return status == AuthorStatus.ACTIVE || status == AuthorStatus.RETIRED;
    }

    public boolean sameAuthorAs(Author other) {
        if (other == null)
            return false;
        if (this.id != null && other.id != null)
            return Objects.equals(this.id, other.id);
        return hasSameBusinessKey(other);
    }

    // the business key is 'email' for now - i am unsure what to choose as the business key currently so i selected email.
    public boolean hasSameBusinessKey(Author other) {
        if (other == null) {
            return false;
        }
        return this.contact.getEmail() != null
                && other.contact.getEmail() != null
                && this.contact.getEmail().equalsIgnoreCase(other.contact.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
