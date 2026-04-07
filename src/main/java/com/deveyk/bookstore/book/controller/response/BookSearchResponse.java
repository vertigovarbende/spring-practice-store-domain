package com.deveyk.bookstore.book.controller.response;

import com.deveyk.bookstore.author.service.domain.AuthorName;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

public record BookSearchResponse(

        String id,
        String title,
        Set<BookSearchAuthorResponse> authors,
        String publisher,
        LocalDate publicationDate,
        String edition,
        String language,
        int pageCount,
        Set<BookGenre> genres,
        String category,
        String status

) {

    @Builder
    public record BookSearchAuthorResponse(
            String id,
            @JsonInclude(JsonInclude.Include.NON_NULL) // ??
            AuthorName name,
            boolean verified
    ) {

    }

}
    /*
    @Getter
    @Builder
    public static class BookSearchResponseAuthor {
        private String id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String penName;
        private boolean isVerified;
    }
    */

