package com.teatro.dto.show;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearShowDto {
	
	@NotBlank
	@Size(max = 200)
	private String titulo;
	
	@Min(0)
	@NotNull
	private float precio;
	
	@NotNull
	private LocalDateTime fechaShow;
	
	@Min(0)
	@Max(240)
	@NotNull
	private int duracionMinShow;
	
	@Size(max = 1500)
	private String descripcion;
	
	@Min(1)
	@NotNull
	private Long categoriaId;
	
	@Min(1)
	@NotNull
	private Long salaId;
}
