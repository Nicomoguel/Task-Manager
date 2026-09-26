package com.springapp.taskapp.controllers;

import com.springapp.taskapp.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleExceptions(
            RuntimeException ex,
            WebRequest request
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        // this line is converting the response to json and giving it back to the client
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        /*
        * The illegal argument exception will propagate to springs
        * exception handling mechanism and spring will find
        * our handler and matches the exeption type
        * that is IllegalArgumentExcepion
        *
        * The handler will then create a response will http status
        * 400 bad request, exception message, and the request details
        *
        * */
    }

}
