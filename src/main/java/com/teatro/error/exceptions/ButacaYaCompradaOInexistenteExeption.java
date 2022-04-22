package com.teatro.error.exceptions;

import com.teatro.modelo.Butaca;

public class ButacaYaCompradaOInexistenteExeption extends RuntimeException {

	private static final long serialVersionUID = 8104384277648612253L;

	public ButacaYaCompradaOInexistenteExeption(Butaca butaca) {
		super("La butaca fila: " + butaca.getFila() + ", numero: " + butaca.getNumero() + " ya fue comprada o no existe");
	}
}
