package com.teatro.error.exceptions;

public class ContrasenasNoCoincidenException extends RuntimeException {
	
	private static final long serialVersionUID = -1596923316558505954L;

	public ContrasenasNoCoincidenException() {
		super("Las contraseñas dadas no coinciden");
	}
}
