package com.software.docifestas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiError> messageError(ResourceNotFoundException ex) {

        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        error.setStatus(404);

        return ResponseEntity
                .status(404)
                .body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> messageError(BusinessException ex) {

        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        error.setStatus(400);

        return ResponseEntity
                .status(400)
                .body(error); }
}