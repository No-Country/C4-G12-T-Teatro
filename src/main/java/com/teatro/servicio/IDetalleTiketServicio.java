package com.teatro.servicio;

import java.util.List;

import com.teatro.modelo.DetalleTiket;

public interface IDetalleTiketServicio {

	
	
	public List<DetalleTiket> ListarDetalle();
	public void crearDetalle ( DetalleTiket detalle);
	public void eliminarDetalle(DetalleTiket detalle);
	public void consultarDetalle ( DetalleTiket detalle); 
	
	
}
