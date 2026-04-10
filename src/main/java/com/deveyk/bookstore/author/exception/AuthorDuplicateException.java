package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsDuplicateException;

public class AuthorDuplicateException extends BsDuplicateException {

    public AuthorDuplicateException(String message) {
        super(message);
    }

    public AuthorDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

}
