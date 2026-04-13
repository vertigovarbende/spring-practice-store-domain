package com.deveyk.bookstore.common.exception;

import java.io.Serial;

public class BsStatusOperationsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BsStatusOperationsException(String message) {
        super(message);
    }

    public BsStatusOperationsException(String message, Throwable cause) {
        super(message, cause);
    }

}
