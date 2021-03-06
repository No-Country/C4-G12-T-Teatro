package com.teatro.controlador;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.teatro.error.ApiError;
import com.teatro.error.exceptions.AlmacenamientoArchivoNoEncontradoException;
import com.teatro.error.exceptions.AlmacenamientoException;
import com.teatro.error.exceptions.ButacaYaCompradaOInexistenteExeption;
import com.teatro.error.exceptions.CategoriaNoEncontradaException;
import com.teatro.error.exceptions.ContrasenasNoCoincidenException;
import com.teatro.error.exceptions.LaSalaYaTieneUnShowEnEseHorarioException;
import com.teatro.error.exceptions.PagoNoExitosoException;
import com.teatro.error.exceptions.PromocionNoEncontradaException;
import com.teatro.error.exceptions.PromocionNoTieneAShowException;
import com.teatro.error.exceptions.PromocionYaTieneAShowException;
import com.teatro.error.exceptions.SalaNoEncontradaException;
import com.teatro.error.exceptions.ShowNoEncontradoException;
import com.teatro.error.exceptions.ShowYaTieneUnaSalaException;
import com.teatro.error.exceptions.UsuarioNoEncontradoException;
import com.teatro.error.exceptions.UsuarioYaExisteException;
import com.teatro.error.exceptions.ValidacionException;

@RestControllerAdvice
public class ErrorControlador extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({AlmacenamientoArchivoNoEncontradoException.class, AlmacenamientoException.class,
		CategoriaNoEncontradaException.class, PromocionNoEncontradaException.class,
		SalaNoEncontradaException.class, ShowNoEncontradoException.class, UsuarioNoEncontradoException.class,})
	public ResponseEntity<ApiError> noEncontradoException(RuntimeException exception){
		return construirErrorResponseEntity(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	@ExceptionHandler({ContrasenasNoCoincidenException.class, ShowYaTieneUnaSalaException.class,
		PromocionNoTieneAShowException.class, PromocionYaTieneAShowException.class,
		ShowYaTieneUnaSalaException.class, PagoNoExitosoException.class})
	public ResponseEntity<ApiError> requestErroneaException(RuntimeException exception){
		return construirErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler({UsuarioYaExisteException.class, ButacaYaCompradaOInexistenteExeption.class,
		LaSalaYaTieneUnShowEnEseHorarioException.class,})
	public ResponseEntity<ApiError> conflitoErrores(RuntimeException exception){
		return construirErrorResponseEntity(HttpStatus.CONFLICT, exception.getMessage());
	}
	
	@ExceptionHandler({ValidacionException.class})
	public ResponseEntity<ApiError> conflitoErrores(ValidacionException exception){
		return construirErrorResponseEntity(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getErrores());
	}
	
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
	
	private ResponseEntity<ApiError> construirErrorResponseEntity(HttpStatus status, String message, List<ObjectError> errores) {
		return ResponseEntity.status(status).body(ApiError.builder()
															.estado(status)
															.mensaje(message)
															.errores(errores)
															.build());
	}
}
