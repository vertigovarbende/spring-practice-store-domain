package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class BookStatusAlreadyChangedException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookStatusAlreadyChangedException(String message) {
        super(message);
    }

    public BookStatusAlreadyChangedException(String message, Throwable cause) {
        super(message, cause);
    }

}
