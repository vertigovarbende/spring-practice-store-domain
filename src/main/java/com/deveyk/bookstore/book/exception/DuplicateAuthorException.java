package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsDuplicateException;

public class DuplicateAuthorException extends BsDuplicateException {

    public DuplicateAuthorException(String message) {
        super(message);
    }

    public DuplicateAuthorException(String message, Throwable cause) {
        super(message, cause);
    }

}
