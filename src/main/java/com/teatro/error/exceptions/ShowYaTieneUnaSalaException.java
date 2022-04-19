package com.teatro.error.exceptions;

public class ShowYaTieneUnaSalaException extends RuntimeException {

	private static final long serialVersionUID = 3413221310886029374L;

	public ShowYaTieneUnaSalaException(String titulo) {
		super("El show " + titulo + " ya se encuentra en una sala");
	}

}
