package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsStatusOperationsException;

import java.io.Serial;

public class BookStatusNotSuitableForOperationException extends BsStatusOperationsException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BookStatusNotSuitableForOperationException(String message) {
        super(message);
    }

    public BookStatusNotSuitableForOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
