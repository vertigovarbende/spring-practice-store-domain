package com.deveyk.bookstore.common.exception;

public class StrategyNotFoundException extends BsNotFoundException {

    public StrategyNotFoundException(String message) {
        super(message);
    }

    public StrategyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
