package com.sailpoint.atmdispenser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionMapper extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<String> handleValidationException(ValidationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please Enter A Valid 4 Digit Pin");
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<String> handleExceptions(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OUT OF SERVICE: Please Try Again Later");
    }
}
