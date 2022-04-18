package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Promocion;


public class PromocionNula extends Promocion {

	public static Promocion construir() {
		return new PromocionNula();
	}

	@Override
	public boolean esNula() {
		return true;
	}
}
