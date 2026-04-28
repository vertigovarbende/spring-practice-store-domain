package com.deveyk.bookstore.category.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class CategoryStatusNotSuitableForOperationException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public CategoryStatusNotSuitableForOperationException(String message) {
        super(message);
    }

    public CategoryStatusNotSuitableForOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
