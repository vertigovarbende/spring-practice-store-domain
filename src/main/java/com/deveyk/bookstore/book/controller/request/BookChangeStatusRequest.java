package com.deveyk.bookstore.book.controller.request;

import com.deveyk.bookstore.book.model.enums.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.util.EnumSet;

public record BookChangeStatusRequest(
        @NotNull(message = "{validation.common.not-null}")
        BookStatus targetStatus
) {

    @JsonIgnore
    @AssertTrue(message = "Book status must be either 'AVAILABLE' or 'UNAVAILABLE'")
    @SuppressWarnings("this method is unused by the app directly")
    public boolean isTargetStatusValid() {
        if (this.targetStatus == null) {
            return true;
        }
        EnumSet<BookStatus> validStatuses = EnumSet.of(
                BookStatus.AVAILABLE,
                BookStatus.UNAVAILABLE);
        return validStatuses.contains(this.targetStatus);
    }
}
