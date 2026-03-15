package com.deveyk.bookstore.common.exception;

import java.io.Serial;

public class BsAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BsAlreadyExistsException(String message) {
        super(message);
    }

    public BsAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
