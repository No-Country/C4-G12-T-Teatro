package com.teatro.Modelo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionPorcentual extends Promocion {

	private float porcentajeAcobrar;
	
	
	public PromocionPorcentual(long id, String titulo, String urlImagen, boolean esActiva, ArrayList<Show> shows, float beneficio) { 
	    super(id, titulo, urlImagen, esActiva, show, beneficio);
	}
	
	
	
}
