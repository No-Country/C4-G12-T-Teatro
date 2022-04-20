package com.teatro.dto.usuario;

import javax.validation.constraints.Email;
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
public class RegistrarUsuarioDto {

	@NotBlank
	@Size(max = 50)
	private String nombre;

	@NotBlank
	@Size(max = 50)
	private String apellido;

	@Email
	@NotNull
	private String email;

	@NotBlank
	@Size(min = 5, max = 15)
	private String contrasena;

	@NotBlank
	@Size(min = 5, max = 15)
	private String contrasenaRepetida;

	@Min(18)
	@Max(120)
	private int edad;

	@NotNull
	@Min(1)
	private Long preferenciaId;
}
