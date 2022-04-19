package com.teatro.error.exceptions;

public class PromocionNoTieneAShowException extends RuntimeException {

	private static final long serialVersionUID = -5477357137285971848L;

	public PromocionNoTieneAShowException(String titulo) {
		super("La promocion no contiene al show " + titulo);
	}
}
