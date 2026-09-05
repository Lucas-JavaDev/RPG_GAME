package com.example.demo.CustomError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {

    private HttpStatus status;
    private String message;

}
