package com.deveyk.bookstore.common.exception;

import java.io.Serial;

public class BsNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6191018889083755865L;

    public BsNotFoundException(String message) {
        super(message);
    }

    public BsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
