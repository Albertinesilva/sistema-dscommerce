package com.swproject.dscommerce.services.exception;

public class DataBaseIntegrityViolationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DataBaseIntegrityViolationException(String msg) {
    super(msg);
  }

}
