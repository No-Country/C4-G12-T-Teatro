package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Show;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShowNulo extends Show {
	
	private static final long serialVersionUID = -2342789798594853155L;

	public static Show construir() {
		return new ShowNulo();
	}

	@Override
	public boolean esNulo() {
		return true;
	}
}
