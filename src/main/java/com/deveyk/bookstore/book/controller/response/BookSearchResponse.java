package com.deveyk.bookstore.book.controller.response;

import com.deveyk.bookstore.author.service.domain.AuthorName;
import com.deveyk.bookstore.book.model.enums.BookGenre;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

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
        BookSearchCategoryResponse category,
        String status

) {
    // refactor this
    @Builder
    public record BookSearchAuthorResponse(
            String id,
            @JsonInclude(JsonInclude.Include.NON_NULL) // ??
            AuthorName name,
            boolean verified
    ) {

    }
    // refactor this
    @Builder
    public record BookSearchCategoryResponse(
            String id,
            String name
    ) {

    }

}

