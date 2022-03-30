package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Show;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ShowNulo extends Show {

	public static Show construir() {
		return new ShowNulo();
	}

	public boolean esNulo() {
		return true;
	}
}
