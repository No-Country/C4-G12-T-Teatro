package com.teatro.dto.sala;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSalaDto {

	private String nombre;
	private int capacidad;
	private int filas;
	private List<ShowTituloDto> shows;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ShowTituloDto{
		
		private long id;
		private String titulo;
		private float precio;
		
		@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
		private LocalDateTime fechaShow;
	}
}
