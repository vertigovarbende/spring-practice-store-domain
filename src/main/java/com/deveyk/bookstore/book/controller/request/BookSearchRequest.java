package com.deveyk.bookstore.book.controller.request;

import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.deveyk.bookstore.common.controller.request.BsPaginationRequest;
import com.deveyk.bookstore.common.model.Filtering;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@NoArgsConstructor
@SuperBuilder
public class BookSearchRequest extends BsPaginationRequest {

    @Valid
    private BookSearchFilter filter;

    private static final Set<String> ACCEPTED_SORT_PROPERTIES = Set.of(
            "title",
            "author",
            "publicationDate"
    );

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookSearchFilter implements Filtering {
        @NotBlank(message = "Search term cannot be blank")
        private String searchTerm;
        @NotBlank(message = "Search type cannot be blank")
        private String searchType;

        private BookStatus status;
    }


    @Override
    public boolean isOrderPropertyAccepted() {
        return this.isPropertyAccepted(ACCEPTED_SORT_PROPERTIES);
    }


}
