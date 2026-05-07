package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

// BsStatusOperationsException???
public class AuthorAlreadyVerifiedException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorAlreadyVerifiedException(String message) {
        super(message);
    }

    public AuthorAlreadyVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

}
