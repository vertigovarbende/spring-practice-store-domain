package com.deveyk.bookstore.book.exception;

import com.deveyk.bookstore.common.exception.BsNotFoundException;

public class StrategyNotFoundException extends BsNotFoundException {

    public StrategyNotFoundException(String message) {
        super(message);
    }

    public StrategyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
