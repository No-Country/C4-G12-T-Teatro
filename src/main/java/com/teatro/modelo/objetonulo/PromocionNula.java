package com.teatro.modelo.objetonulo;

import com.teatro.modelo.Promocion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PromocionNula extends Promocion{

	public Promocion construir() {
		return new PromocionNula();
	}
	
	@Override
	public boolean esNula() {
		return true;
	}

}
