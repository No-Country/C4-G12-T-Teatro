package com.teatro.error.exceptions;

public class AlmacenamientoArchivoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -6482024217700321320L;

	public AlmacenamientoArchivoNoEncontradoException(String mensaje) {
		super(mensaje);
	}

	public AlmacenamientoArchivoNoEncontradoException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
