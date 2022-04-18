package com.teatro.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Porcentual")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PromocionPorcentual extends Promocion implements Serializable{

	private static final long serialVersionUID = 4206942871044160893L;
	
	private float porcentajeACobrar;

	public PromocionPorcentual(String titulo, String urlImagen, List<Show> shows, float descuento) {
		super(null, titulo, urlImagen, true, shows);
		this.porcentajeACobrar = (float) ((100 - descuento) / 100.0);
	}

	@Override
	public float getPrecio() {
		return super.getPrecio() * porcentajeACobrar;
	}

	@Override
	public boolean esNula() {
		return false;
	}
}