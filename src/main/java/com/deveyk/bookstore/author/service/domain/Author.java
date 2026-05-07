package com.deveyk.bookstore.author.service.domain;

import com.deveyk.bookstore.author.exception.*;
import com.deveyk.bookstore.author.model.enums.AuthorStatus;
import com.deveyk.bookstore.book.service.domain.Book;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class Author {

    private UUID id;
    private AuthorName name;
    private AuthorContact contact;
    private AuthorStatus status;
    @Builder.Default
    private Boolean isVerified = false;

    public static Author create(AuthorName name, AuthorContact contact) {
        validateName(name);
        validateContact(contact);

        return Author.builder()
                .id(UUID.randomUUID())
                .name(name)
                .contact(contact)
                .status(AuthorStatus.ACTIVE)
                .isVerified(false)
                .build();
    }

    // Refactor???
    public void updateName(AuthorName name) {
        validateUpdatable();
        validateName(name);

        this.name = name;
    }

    // Refactor???
    public void updateContact(AuthorContact contact) {
        validateUpdatable();
        validateContact(contact);

        this.contact = contact;
    }

    public void changeStatus(AuthorStatus targetStatus) {
        Objects.requireNonNull(targetStatus, "Target status cannot be null");

        if (this.status == targetStatus) {
            throw new AuthorStatusAlreadyChangedException(
                    "Author is already in " + targetStatus.name() + " status"
            );
        }

        if (!this.status.canTransitionTo(targetStatus)) {
            throw new AuthorStatusNotSuitableForOperationException(
                    "Author status cannot be changed from "
                            + this.status.name()
                            + " to "
                            + targetStatus.name()
            );
        }

        this.status = targetStatus;
    }

    public void verify() {
        if (Boolean.TRUE.equals(this.isVerified)) {
            throw new AuthorAlreadyVerifiedException("Author is already verified");
        }

        this.isVerified = true;
    }

    public void unverify() {
        if (Boolean.FALSE.equals(this.isVerified)) {
            throw new AuthorAlreadyVerifiedException("Author is already unverified");
        }

        this.isVerified = false;
    }

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
        return other != null
                && this.contact != null
                && other.contact != null
                && this.contact.hasEmail()
                && other.contact.hasEmail()
                && this.contact.getEmail().equalsIgnoreCase(other.contact.getEmail());
    }

    private void validateUpdatable() {
        if (this.status == AuthorStatus.DECEASED) {
            throw new AuthorStatusNotSuitableForOperationException(
                    "Deceased author cannot be updated"
            );
        }
    }

    private static void validateName(AuthorName name) {
        if (name == null || !name.hasRequiredLegalName()) {
            throw new AuthorNameNotEligibleException(
                    "Author must have first name and last name"
            );
        }
    }

    private static void validateContact(AuthorContact contact) {
        if (contact == null || !contact.hasEmail()) {
            throw new AuthorContactNotEligibleException(
                    "Author email is required"
            );
        }

        if (!contact.canBeContactedByPreferredMethod()) {
            throw new AuthorContactNotEligibleException(
                    "Author cannot be contacted by preferred contact method"
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return this.id != null && this.id.equals(author.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
