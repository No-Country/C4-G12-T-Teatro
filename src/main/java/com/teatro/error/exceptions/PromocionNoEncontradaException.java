package com.teatro.error.exceptions;

public class PromocionNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -3052827406874795856L;

	public PromocionNoEncontradaException(Long idPromocion) {
		super("La promocion con id: " + idPromocion + " no fue encontrada.");
	}

}
