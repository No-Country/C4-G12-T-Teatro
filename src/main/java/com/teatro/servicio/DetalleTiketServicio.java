package com.teatro.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.DetalleTiket;
import com.teatro.repositorio.DetalleTiketRepositorio;

@Service 
public class DetalleTiketServicio implements IDetalleTiketServicio {

	@Autowired
	public DetalleTiketRepositorio  detalleServicio;
	
	@Override
	public List<DetalleTiket> ListarDetalle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearDetalle(DetalleTiket detalle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarDetalle(DetalleTiket detalle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consultarDetalle(DetalleTiket detalle) {
		// TODO Auto-generated method stub
		
	}

}
