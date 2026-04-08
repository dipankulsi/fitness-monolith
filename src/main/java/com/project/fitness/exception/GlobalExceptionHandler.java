package com.project.fitness.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleException(MethodArgumentNotValidException e) {

        Map<String,Object> errorMsg = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error-> errorMsg.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
