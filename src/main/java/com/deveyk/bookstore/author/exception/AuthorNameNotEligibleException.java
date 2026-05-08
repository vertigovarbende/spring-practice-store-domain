package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsNotEligibleException;

import java.io.Serial;

public class AuthorNameNotEligibleException extends BsNotEligibleException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public AuthorNameNotEligibleException(String message) {
        super(message);
    }

    public AuthorNameNotEligibleException(String message, Throwable cause) {
        super(message, cause);
    }

}
