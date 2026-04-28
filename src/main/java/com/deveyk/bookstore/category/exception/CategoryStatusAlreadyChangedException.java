package com.deveyk.bookstore.category.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class CategoryStatusAlreadyChangedException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public CategoryStatusAlreadyChangedException(String message) {
        super(message);
    }

    public CategoryStatusAlreadyChangedException(String message, Throwable cause) {
        super(message, cause);
    }
}
