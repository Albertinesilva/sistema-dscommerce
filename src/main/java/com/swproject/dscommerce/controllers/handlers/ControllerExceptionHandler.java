package com.swproject.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.swproject.dscommerce.dto.CustomError;
import com.swproject.dscommerce.services.exception.DataBaseIntegrityViolationException;
import com.swproject.dscommerce.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError err = new CustomError(Instant.now(), status.value(), "Resource not found", e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DataBaseIntegrityViolationException.class)
  public ResponseEntity<CustomError> dataBase(DataBaseIntegrityViolationException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.CONFLICT;
    CustomError err = new CustomError(Instant.now(), status.value(),
        "Referential integrity failure.",
        e.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }
}
