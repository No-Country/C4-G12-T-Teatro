package com.teatro.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Porcentual")
@NoArgsConstructor
@AllArgsConstructor
public class PromocionPorcentual extends Promocion {
	
	private float porcentajeACobrar;
	
	@Override
	public float getPrecio() {
		return super.getPrecio() * porcentajeACobrar;
	}
	
	@Override
	public boolean esNula() {
		return false;
	}

}
