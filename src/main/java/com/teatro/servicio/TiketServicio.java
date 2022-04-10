package com.teatro.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Tiket;
import com.teatro.repositorio.TiketRepositorio;

@Service
public class TiketServicio implements ITiketServicio {

	
	
	@Autowired
	public TiketRepositorio tikets;

	    @Override
	    public List<Tiket> verTikets() {
	        return tikets.findAll();
	    }

	    @Override
	    public void crear(Tiket tiket) {
	  	tikets.save(tiket);
	    	
	}
	
	
	

}
