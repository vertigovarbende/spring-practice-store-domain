package com.deveyk.bookstore.book.controller.request;

import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

/*
- if the number of authors does not match the number of found authors, throw an error
- if there is an invalid genre, throw an error
- everything should be within a single transaction
 */
public record BookCreateRequest(

        @NotBlank(message = "{validation.common.not-blank}")
        @Size(max = 255, message = "{validation.common.size}")
        String title,

        BookStatus status,

        String isbn10,

        String isbn13,

        @Size(max = 255, message = "{validation.common.size}")
        String publisher,

        @PastOrPresent(message = "{validation.common.insert-invalid}")
        LocalDate publicationDate,

        @Size(max = 100, message = "{validation.common.size}")
        String edition,

        @Size(max = 50, message = "{validation.common.size}")
        String language,

        @Positive(message = "{validation.common.insert-invalid}")
        Integer pageCount,

        @Size(max = 100, message = "{validation.common.size}")
        UUID category,

        @Size(max = 10, message = "{validation.common.size}")
        Set<UUID> authorIds,

        @Size(max = 10, message = "{validation.common.size}")
        Set<@NotBlank(message = "{validation.common.not-blank}") String> genres

) {

    @JsonIgnore
    @AssertTrue(message = "Book status must be either 'AVAILABLE' or 'UNAVAILABLE'")
    @SuppressWarnings("this method is unused by the app directly")
    private boolean isBookStatusValid() {
        if (this.status == null) {
            return true;
        }
        EnumSet<BookStatus> validStatuses = EnumSet.of(
                BookStatus.AVAILABLE,
                BookStatus.UNAVAILABLE);
        return validStatuses.contains(this.status);
    }

}