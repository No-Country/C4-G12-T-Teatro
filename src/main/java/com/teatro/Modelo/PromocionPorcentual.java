package com.teatro.modelo;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("porcentual")
@EqualsAndHashCode(callSuper = true)
public class PromocionPorcentual extends Promocion {

	private float porcentajeAcobrar;

	public PromocionPorcentual(String titulo, String urlImagen, List<Show> shows, float descuento) {
		super(null, titulo, urlImagen, true, shows);
		this.porcentajeAcobrar = (float) ((100 - descuento) / 100.0);
	}

	@Override
	public float getPrecio() {
		return super.getPrecio() * porcentajeAcobrar;
	}

	@Override
	public boolean esNulo() {
		return false;
	}
}
