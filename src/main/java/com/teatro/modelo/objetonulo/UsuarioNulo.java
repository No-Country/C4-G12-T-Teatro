package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Usuario;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioNulo extends Usuario{

	private static final long serialVersionUID = 2773496369831665351L;

	public static Usuario construir() {
		return new UsuarioNulo();
	}

	@Override
	public boolean esNulo() {
		return true;
	}
}
