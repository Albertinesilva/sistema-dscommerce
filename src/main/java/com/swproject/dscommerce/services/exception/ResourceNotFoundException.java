package com.swproject.dscommerce.services.exception;

public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }

  public ResourceNotFoundException(Long id) {
    super("Product not found. Please check the provided ID: " + id);
  }

}
