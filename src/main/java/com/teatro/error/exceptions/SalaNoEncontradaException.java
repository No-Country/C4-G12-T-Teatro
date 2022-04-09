package com.teatro.error.exceptions;

public class SalaNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -2394566363661066981L;

	public SalaNoEncontradaException() {
		super("No se encontro la sala solicitada");
	}

	public SalaNoEncontradaException(Long id) {
		super("No se encontro la sala con el id: " + id);
	}

}

