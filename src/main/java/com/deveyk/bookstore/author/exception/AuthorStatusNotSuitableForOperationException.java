package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class AuthorStatusNotSuitableForOperationException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorStatusNotSuitableForOperationException(String message) {
        super(message);
    }

    public AuthorStatusNotSuitableForOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
