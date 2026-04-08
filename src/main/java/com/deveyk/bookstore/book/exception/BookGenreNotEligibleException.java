package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsNotEligibleException;

import java.io.Serial;

public class BookGenreNotEligibleException extends BsNotEligibleException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookGenreNotEligibleException(String message) {
        super(message);
    }

    public BookGenreNotEligibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
