package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Promocion;

public class PromocionNula extends Promocion{

	@Override
	public boolean esNula() {
		return true;
	}

	public static Promocion construir() {
		return new PromocionNula();
	}
}
