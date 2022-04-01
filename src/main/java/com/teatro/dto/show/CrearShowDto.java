package com.teatro.dto.show;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CrearShowDto {
	private String titulo;
	private float precio;
	private LocalDateTime fechaShow;
	private int duracionMinShow;
	private String descripcion;
	private Long categoriaId;
	private Long salaId;
	private ArrayList<Long> promocionId;
}
