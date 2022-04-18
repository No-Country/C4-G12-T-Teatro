package com.teatro.error.exceptions;

public class ShowNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 574413035728744149L;

	public ShowNoEncontradoException(Long idShow) {
		super("El show con id: " + idShow + " no fue encontrado.");
	}

}
