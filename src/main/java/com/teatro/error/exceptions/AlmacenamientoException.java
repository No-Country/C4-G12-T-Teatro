package com.teatro.error.exceptions;

public class AlmacenamientoException extends RuntimeException {

	private static final long serialVersionUID = -7347345983494933818L;

	public AlmacenamientoException(String mensaje) {
		super(mensaje);
	}

	public AlmacenamientoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
