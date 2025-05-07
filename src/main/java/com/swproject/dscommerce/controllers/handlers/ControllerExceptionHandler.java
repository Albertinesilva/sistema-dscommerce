package com.swproject.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.swproject.dscommerce.dto.CustomError;
import com.swproject.dscommerce.dto.ValidationError;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e,
      HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError err = new ValidationError(Instant.now(), status.value(),
        "Validation failed for the provided arguments.",
        "invalid data",
        request.getRequestURI());

    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      err.addError(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.status(status).body(err);
  }
}
