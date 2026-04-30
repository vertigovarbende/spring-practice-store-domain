package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsAlreadyExistsException;

import java.io.Serial;

public class BookAlreadyExistsException extends BsAlreadyExistsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
