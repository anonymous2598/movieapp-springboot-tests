package com.stackroute.movieservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieAlreadyExists.class)
    public ResponseEntity<?> MovieAlreadyExistsException(MovieAlreadyExists ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MovieDoesNotExist.class)
    public ResponseEntity<?> MovieDoesNotExistException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> SQLDataException(Exception ec, WebRequest request)
    {
        return new ResponseEntity<>(ec.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
