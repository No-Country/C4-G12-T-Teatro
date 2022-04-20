package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Promocion;

public class PromocionNula extends Promocion {

	private static final long serialVersionUID = 7743063926389172118L;

	public static Promocion construir() {
		return new PromocionNula();
	}

	@Override
	public boolean esNula() {
		return true;
	}
}
