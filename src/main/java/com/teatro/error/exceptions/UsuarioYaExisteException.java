package com.teatro.error.exceptions;

public class UsuarioYaExisteException extends RuntimeException {

	private static final long serialVersionUID = 6217872859151488446L;

	public UsuarioYaExisteException(String email) {
	super("Ya existe un usuario registrado con el mail " + email);
	}

}
