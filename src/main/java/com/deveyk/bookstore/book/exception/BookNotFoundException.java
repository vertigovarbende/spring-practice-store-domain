package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsNotFoundException;

import java.io.Serial;

public class BookNotFoundException extends BsNotFoundException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
