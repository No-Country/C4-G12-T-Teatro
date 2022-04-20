package com.teatro.error.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ValidacionException extends RuntimeException {

	private static final long serialVersionUID = 4061321937936965451L;
	
	private final List<ObjectError> errores;
	
	public ValidacionException(List<ObjectError> errores) {
		super();
		this.errores = errores;
	}

	public List<ObjectError> getErrores(){
		return this.errores;
	}
}
