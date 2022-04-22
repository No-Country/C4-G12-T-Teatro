package com.teatro.error.exceptions;

import java.time.LocalDateTime;

import com.teatro.modelo.Sala;

public class LaSalaYaTieneUnShowEnEseHorarioException extends RuntimeException {

	private static final long serialVersionUID = -8972999579357681L;

	public LaSalaYaTieneUnShowEnEseHorarioException(Sala sala, LocalDateTime desde, LocalDateTime hasta) {
		super("La sala " + sala.getNombre() + " ya tiene un show asignado entre las fechas " + desde + " y " + hasta);
	}

}
