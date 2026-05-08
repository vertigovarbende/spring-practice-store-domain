package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class AuthorStatusAlreadyChangedException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorStatusAlreadyChangedException(String message) {
        super(message);
    }

    public AuthorStatusAlreadyChangedException(String message, Throwable cause) {
        super(message, cause);
    }
}
