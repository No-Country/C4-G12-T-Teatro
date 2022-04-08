package com.teatro.dto.show;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearShowDto {

	private String titulo;
	private float precio;
	private LocalDateTime fechaShow;
	private int duracionMinShow;
	private String descripcion;
	private Long categoriaId;
	private Long salaId;
}
