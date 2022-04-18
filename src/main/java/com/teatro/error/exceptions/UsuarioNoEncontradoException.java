package com.teatro.error.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8147285171439890280L;

	public UsuarioNoEncontradoException(String email) {
		super("usuario " + email + " no fue encontrado");
	}

}
