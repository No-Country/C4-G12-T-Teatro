package com.teatro.dto.show;

import java.time.LocalDateTime;

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
public class GetShowDto {

	private String titulo;
	private float precio;
	private String urlStrig;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime fechaShow;
	private int duracionMinShow;
	private String descripcion;
	private String categoriaNombre;
	private String salaNombre;
}
