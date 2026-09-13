package com.example.demo.CustomError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {

    List<FieldMessage> fieldErrors = new ArrayList<>();

    private HttpStatus status;
    private String message;


    public void addError(String fieldName, String defaultMessage) {
        fieldErrors.add(new FieldMessage(fieldName, defaultMessage));
    }

    public CustomError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
