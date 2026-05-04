package com.deveyk.bookstore.category.exception;

import com.deveyk.bookstore.common.exception.BsNotEligibleException;

import java.io.Serial;

public class CategoryNotEligibleException extends BsNotEligibleException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public CategoryNotEligibleException(String message) {
        super(message);
    }

    public CategoryNotEligibleException(String message, Throwable cause) {
        super(message, cause);
    }

}
