package com.teatro.Modelo.objetonulo;

import com.teatro.Modelo.Promocion;

public class PromocionNula extends Promocion {

	@Override
	public boolean esNulo() {
		return true;
	}
	
	public Promocion construir() {
		return new PromocionNula();
	}

	
}
