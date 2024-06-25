package com.samanecorp.thymeleaf1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<APIException> handleEntityNotFoundException(EntityNotFoundException e) {
        APIException exception = new APIException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<APIException> handleDataNotFoundException(DataNotFoundException e) {
        APIException exception = new APIException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
