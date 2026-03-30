package com.deveyk.bookstore.common.exception;

import java.io.Serial;

public class BsDuplicateException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -6191018889083755865L;

  public BsDuplicateException(String message) {
    super(message);
  }

  public BsDuplicateException(String message, Throwable cause) {
    super(message, cause);
  }

}
