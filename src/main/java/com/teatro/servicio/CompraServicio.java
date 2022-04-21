package com.teatro.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.teatro.dto.butaca.MapFilaButacaDto;
import com.teatro.modelo.Butaca;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraServicio {
	
	public Tiket comprar(Usuario usuario, Promocion promocion, MapFilaButacaDto butacas) {
		int precioTotal = 0;
		List<Butaca> butacasTiket = new ArrayList<>();
		for (Entry<Integer, Integer[]> butacas : butacas.getButacas().entrySet()) {
			for (Integer butaca : butaca.getValue()) {
				Butaca but = new Butaca();
			}
		}
		
		
		Tiket tiket = Tiket.builder().cantidadEntradas(butacas.getCantidad())
										.comprador(usuario)
										.precio(precioTotal)
										.butacas(null)
										.descripcion(null)
										.show(promocion.getShow())
										.build();
	}
}
