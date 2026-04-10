package com.deveyk.bookstore.author.exception;

import com.deveyk.bookstore.common.exception.BsNotEligibleException;

public class AuthorNotEligibleException extends BsNotEligibleException {

    public AuthorNotEligibleException(String message) {
        super(message);
    }

    public AuthorNotEligibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
