package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Tiket;

public class TiketNulo extends Tiket {

	private static final long serialVersionUID = -7389463043156265417L;

	public static Tiket construir() {
		return new TiketNulo();
	}

	@Override
	public boolean esNulo() {
		return true;
	}
}
