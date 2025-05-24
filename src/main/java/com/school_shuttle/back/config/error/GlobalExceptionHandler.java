package com.school_shuttle.back.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErroCustomizado.class)
    public ResponseEntity<?> handleErroCustomizado(ErroCustomizado ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(java.util.Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(java.util.Map.of("message", ex.getMessage() != null ? ex.getMessage() : "Entidade não encontrada"));
    }
}