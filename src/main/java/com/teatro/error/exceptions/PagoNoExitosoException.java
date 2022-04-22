package com.teatro.error.exceptions;

public class PagoNoExitosoException extends RuntimeException {
	
	private static final long serialVersionUID = 4635999166939627937L;
	
	public PagoNoExitosoException() {
		super("El pago no se pudo realizar.");
	}
}
