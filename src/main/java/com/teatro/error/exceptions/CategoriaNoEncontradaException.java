package com.teatro.error.exceptions;

public class CategoriaNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -7179862357134026006L;

	public CategoriaNoEncontradaException() {
		super("No se encontro la categoria solicitada");
	}

	public CategoriaNoEncontradaException(Long id) {
		super("No se encontro la categoria con el id: " + id);
	}
}
