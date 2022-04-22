package com.teatro.dto.sala;

import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
public class CrearSalaDto {

	@NotBlank
	@Size(max = 60)
	private String nombre;

	@Min(100)
	@Max(5000)
	@NotNull
	private int capacidad;

	@Min(10)
	@Max(100)
	@NotNull
	private int filas;

	private List<ShowIdDto> showsShowId;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ShowIdDto {

		private Long id;
		
	}
}
