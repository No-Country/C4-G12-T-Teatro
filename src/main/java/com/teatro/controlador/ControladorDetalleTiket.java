package com.teatro.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.modelo.DetalleTiket;
import com.teatro.servicio.DetalleTiketServicio;

@RestController
public class ControladorDetalleTiket {
	@Autowired 
	public DetalleTiketServicio detalleServicio;
	
	@PostMapping("/detalle/new")
	public ResponseEntity<?> crear( @RequestBody DetalleTiket detalle)
	
	{
		detalleServicio.crearDetalle(detalle);
		 return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping ("/detalle/listar")
	@ResponseBody
	public List<DetalleTiket> listadoDetalle(){
          
	   return detalleServicio.ListarDetalle(); 
		
	
	}
	
}
