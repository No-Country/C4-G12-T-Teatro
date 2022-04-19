package com.teatro.dto.promocion;

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
public class GetPromocionDto {

	private String titulo;
	
	private String urlImagen;
	
	private String showTitulo;	
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime showFechaShow;
	
	private int showDuracionMinShow;
	
	private String descripcion;
	
	private String showCategoriaNombre;
	
	private float precio;
}
