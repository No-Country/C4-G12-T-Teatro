package com.teatro.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsuarioDto {

	private String nombreCompleto;
	private String email;
	private int edad;
	private String preferenciaNombre;

}
