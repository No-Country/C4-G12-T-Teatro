package com.teatro.modelo;

import java.io.Serializable;

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
	
	private float descuento;

	public PromocionPorcentual(Long id,String titulo, String urlImagen ,Show show, float descuento) {
		super(id, titulo, urlImagen, show);
		this.descuento = descuento;
	}

	@Override
	public float getPrecio() {
		return super.getPrecio() * ( 1 - ( descuento / 100) );
	}

	@Override
	public boolean esNula() {
		return false;
	}
}