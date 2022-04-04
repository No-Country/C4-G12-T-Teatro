package com.teatro.Modelo;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Porcentual")
@EqualsAndHashCode(callSuper = true)
public class PromocionPorcentual extends Promocion {

	
	private float porcentajeAcobrar;
	
	public PromocionPorcentual(String titulo, String urlImagen, List<Show> shows, float descuento) { 
	    super(null, titulo, urlImagen, true, shows);
	    this.porcentajeAcobrar = (float)((100-descuento)/100.0);
	}
	
	@Override
	public float getPrecio() {
		return super.getPrecio()*porcentajeAcobrar;
	}


	@Override
	public boolean esNulo() {
		return false;
	}
	
	
	
}
