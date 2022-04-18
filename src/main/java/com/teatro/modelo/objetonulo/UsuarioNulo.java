package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Usuario;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioNulo extends Usuario{

	public static Usuario construir() {
		return new UsuarioNulo();
	}

	@Override
	public boolean esNulo() {
		return true;
	}
}
