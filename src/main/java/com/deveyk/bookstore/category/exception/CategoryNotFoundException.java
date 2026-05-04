package com.deveyk.bookstore.category.exception;

import com.deveyk.bookstore.common.exception.BsNotFoundException;

import java.io.Serial;

public class CategoryNotFoundException extends BsNotFoundException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
