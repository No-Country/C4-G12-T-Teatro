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
public class EditarUsuarioDto {

	@NotBlank
	@Size(max = 50)
	private String nombre;

	@NotBlank
	@Size(max = 50)
	private String apellido;

	@Email
	@NotNull
	private String email;

	@Min(18)
	@Max(120)
	private int edad;

	@NotNull
	@Min(1)
	private Long preferenciaId;
}
