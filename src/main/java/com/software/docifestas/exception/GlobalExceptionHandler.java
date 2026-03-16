package com.software.docifestas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> messageError(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}
