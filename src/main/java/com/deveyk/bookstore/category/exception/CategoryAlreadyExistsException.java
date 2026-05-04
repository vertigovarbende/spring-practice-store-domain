package com.deveyk.bookstore.category.exception;

import com.deveyk.bookstore.common.exception.BsAlreadyExistsException;

import java.io.Serial;

public class CategoryAlreadyExistsException extends BsAlreadyExistsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public CategoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
