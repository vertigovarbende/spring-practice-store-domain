package com.deveyk.bookstore.common.exception;

import java.io.Serial;

public class BsNotEligibleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BsNotEligibleException(String message) {
        super(message);
    }

    public BsNotEligibleException(String message, Throwable cause) {
        super(message, cause);
    }

}
