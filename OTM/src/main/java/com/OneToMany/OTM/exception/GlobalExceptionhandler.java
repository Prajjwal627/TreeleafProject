package com.OneToMany.OTM.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle specific expection
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException
    (ResourceNotFoundException exception, WebRequest request) {
        ErrorDetails errrorDetails = new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException
            (APIException exception, WebRequest request) {
        ErrorDetails errrorDetails = new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errrorDetails, HttpStatus.NOT_FOUND);
    }
    //handle global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException
    (Exception exception, WebRequest request) {
        ErrorDetails errrorDetails = new ErrorDetails(new Date(), exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(errrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}