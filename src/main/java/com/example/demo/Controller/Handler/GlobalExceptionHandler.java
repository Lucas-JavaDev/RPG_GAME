package com.example.demo.Controller.Handler;


import com.example.demo.CustomError.CustomError;
import com.example.demo.Exception.InvalidTurnException;
import com.example.demo.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(InvalidTurnException.class)
    public ResponseEntity<CustomError> resourceNotFoundHandler(InvalidTurnException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError customError = new CustomError(status, e.getMessage());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        CustomError customError = new CustomError(status, "Invalid Data");

        for(FieldError field : e.getBindingResult().getFieldErrors()) {
            customError.addError(field.getField(), field.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomError> httpNotReableHandler(HttpMessageNotReadableException e) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        CustomError customError = new CustomError(status, "Could not convert String to Enum");

        return ResponseEntity.status(status).body(customError);
    }

}
