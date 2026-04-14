package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsNotFoundException;

import java.io.Serial;

public class AuthorNotFoundException extends BsNotFoundException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
