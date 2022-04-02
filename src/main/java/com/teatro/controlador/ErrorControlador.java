package com.teatro.controlador;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.teatro.error.ApiError;

@RestControllerAdvice
public class ErrorControlador extends ResponseEntityExceptionHandler{
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

	private ResponseEntity<ApiError> construirErrorResponseEntity(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(ApiError.builder()
															.estado(status)
															.mensaje(message)
															.build());
	}
}
