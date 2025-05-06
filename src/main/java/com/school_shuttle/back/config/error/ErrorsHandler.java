package com.school_shuttle.back.config.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorsHandler {

	/* Formatando erros 404 */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleError404(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

	/* Formatando erros 400 por validações */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleError400(MethodArgumentNotValidException exception) {
		var errors = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorsDTO::new).toList());
	}

	/* Formatando erros customizados */
    @ExceptionHandler(ErroCustomizado.class)
    public ResponseEntity<?> handleCustomError(ErroCustomizado exception) {
        return ResponseEntity.badRequest().body(new ErroCustomizadoDTO(exception));
    }

	private record ValidationErrorsDTO(String field, String message) {
		public ValidationErrorsDTO(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

	private record ErroCustomizadoDTO(String message) {
		public ErroCustomizadoDTO(ErroCustomizado erro) {
			this(erro.getMessage());
		}
	}

}
