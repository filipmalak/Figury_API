package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalnyHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> obsłużWalidację(MethodArgumentNotValidException ex) {
        Map<String, String> błędy = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
            błędy.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(błędy, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> obsłużInne(Exception ex) {
        return new ResponseEntity<>("Błąd: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
