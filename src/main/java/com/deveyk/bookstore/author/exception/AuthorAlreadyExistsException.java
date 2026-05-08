package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsAlreadyExistsException;

import java.io.Serial;

public class AuthorAlreadyExistsException extends BsAlreadyExistsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }

    public AuthorAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
