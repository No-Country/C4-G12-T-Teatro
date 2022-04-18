package com.teatro.error.exceptions;

public class PromocionYaTieneAShowException extends RuntimeException {

	private static final long serialVersionUID = 382173965418914017L;

	public PromocionYaTieneAShowException(String titulo) {
		super("La promocion ya contiene al show " + titulo);
	}

}
