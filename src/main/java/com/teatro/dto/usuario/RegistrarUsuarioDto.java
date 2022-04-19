package com.teatro.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarUsuarioDto {

	private String email;
	private String contrasena;
	private String contrasenaRepetida;
}
