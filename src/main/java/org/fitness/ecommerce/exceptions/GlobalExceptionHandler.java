package org.fitness.ecommerce.exceptions;

import org.fitness.ecommerce.responses.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleException(Exception e){
        GlobalResponse response = new GlobalResponse();
        response.setData(null);
        response.setStatus(0);
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
