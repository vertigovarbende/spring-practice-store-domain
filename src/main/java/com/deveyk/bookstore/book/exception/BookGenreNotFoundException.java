package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsNotFoundException;

import java.io.Serial;

public class BookGenreNotFoundException extends BsNotFoundException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookGenreNotFoundException(String message) {
        super(message);
    }

    public BookGenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
