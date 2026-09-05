package com.example.demo.Controller.Handler;


import com.example.demo.CustomError.CustomError;
import com.example.demo.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFoundHandler(ResourceNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(status, e.getMessage());
        return ResponseEntity.status(status).body(customError);
    }

}
