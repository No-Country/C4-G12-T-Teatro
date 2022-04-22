package com.teatro.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.teatro.dto.butaca.MapFilaButacaDto;
import com.teatro.error.exceptions.ButacaYaCompradaOInexistenteExeption;
import com.teatro.modelo.Butaca;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Show;
import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.modelo.objetonulo.TiketNulo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraServicio {
	
	private final TiketServicio tiketServicio;
	private final ShowServicio showServicio;
	
	@Transactional
	public Tiket comprar(Usuario usuario, Promocion promocion, MapFilaButacaDto butacasCompradas) {
		float precioTotal = promocion.getPrecio() * butacasCompradas.getCantidad();
		List<Butaca> butacasTiket = new ArrayList<>();
		Show show = promocion.getShow();
		
		for (Entry<Integer, Integer[]> butacas : butacasCompradas.getButacas().entrySet()) {
			for (Integer butacaNumero : butacas.getValue()) {
				Butaca butaca = new Butaca();
				butaca.setFila(butacas.getKey());
				butaca.setNumero(butacaNumero);
				
				if(show.yaTieneA(butaca) || !show.getSala().esButacaExistenteEnSala(butaca)) {
					throw new ButacaYaCompradaOInexistenteExeption(butaca);
				}
				
				show.ocuparButaca(butaca);
				butacasTiket.add(butaca);
			}
		}
		Tiket tiket = Tiket.builder().cantidadEntradas(butacasCompradas.getCantidad())
										.comprador(usuario)
										.precio(precioTotal)
										.butacas(butacasTiket)
										.nombreApellido(usuario.getNombre() + " " + usuario.getApellido())
										.show(show)
										.build();
		
		if(!esPagoConMercadoPagoExitoso()) {
			return TiketNulo.construir();
		}
		showServicio.guardar(show);
		
		return tiketServicio.guardar(tiket);
	}

	@Transactional
	public Tiket comprar(Promocion promocion, MapFilaButacaDto butacasCompradas) {
		float precioTotal = promocion.getPrecio() * butacasCompradas.getCantidad();
		List<Butaca> butacasTiket = new ArrayList<>();
		Show show = promocion.getShow();
		
		for (Entry<Integer, Integer[]> butacas : butacasCompradas.getButacas().entrySet()) {
			for (Integer butacaNumero : butacas.getValue()) {
				Butaca butaca = new Butaca();
				butaca.setFila(butacas.getKey());
				butaca.setNumero(butacaNumero);
				
				if(show.yaTieneA(butaca) || !show.getSala().esButacaExistenteEnSala(butaca)) {
					throw new ButacaYaCompradaOInexistenteExeption(butaca);
				}
				
				show.ocuparButaca(butaca);
				butacasTiket.add(butaca);
			}
		}
		Tiket tiket = Tiket.builder().cantidadEntradas(butacasCompradas.getCantidad())
										.comprador(null)
										.precio(precioTotal)
										.butacas(butacasTiket)
										.nombreApellido("invitado")
										.show(show)
										.build();
		
		
		if(!esPagoConMercadoPagoExitoso()) {
			return TiketNulo.construir();
		}
		showServicio.guardar(show);
		
		return tiketServicio.guardar(tiket);
	}

	

	public Tiket comprar(Usuario usuario, Show show, MapFilaButacaDto butacasCompradas) {
		float precioTotal = show.getPrecio() * butacasCompradas.getCantidad();
		List<Butaca> butacasTiket = new ArrayList<>();
		
		for (Entry<Integer, Integer[]> butacas : butacasCompradas.getButacas().entrySet()) {
			for (Integer butacaNumero : butacas.getValue()) {
				Butaca butaca = new Butaca();
				butaca.setFila(butacas.getKey());
				butaca.setNumero(butacaNumero);
				
				if(show.yaTieneA(butaca) || !show.getSala().esButacaExistenteEnSala(butaca)) {
					throw new ButacaYaCompradaOInexistenteExeption(butaca);
				}
				
				show.ocuparButaca(butaca);
				butacasTiket.add(butaca);
			}
		}
		Tiket tiket = Tiket.builder().cantidadEntradas(butacasCompradas.getCantidad())
										.comprador(usuario)
										.precio(precioTotal)
										.butacas(butacasTiket)
										.nombreApellido(usuario.getNombre() + " " + usuario.getApellido())
										.show(show)
										.build();
		
		if(!esPagoConMercadoPagoExitoso()) {
			return TiketNulo.construir();
		}
		showServicio.guardar(show);
		
		return tiketServicio.guardar(tiket);
	}

	public Tiket comprar(Show show, MapFilaButacaDto butacasCompradas) {
		float precioTotal = show.getPrecio() * butacasCompradas.getCantidad();
		List<Butaca> butacasTiket = new ArrayList<>();
		
		for (Entry<Integer, Integer[]> butacas : butacasCompradas.getButacas().entrySet()) {
			for (Integer butacaNumero : butacas.getValue()) {
				Butaca butaca = new Butaca();
				butaca.setFila(butacas.getKey());
				butaca.setNumero(butacaNumero);
				
				if(show.yaTieneA(butaca) || !show.getSala().esButacaExistenteEnSala(butaca)) {
					throw new ButacaYaCompradaOInexistenteExeption(butaca);
				}
				
				show.ocuparButaca(butaca);
				butacasTiket.add(butaca);
			}
		}
		Tiket tiket = Tiket.builder().cantidadEntradas(butacasCompradas.getCantidad())
										.comprador(null)
										.precio(precioTotal)
										.butacas(butacasTiket)
										.nombreApellido("invitado")
										.show(show)
										.build();
		
		
		if(!esPagoConMercadoPagoExitoso()) {
			return TiketNulo.construir();
		}
		showServicio.guardar(show);
		
		return tiketServicio.guardar(tiket);
	}
	
	private boolean esPagoConMercadoPagoExitoso() {
		// Logica Mercado Pago para hacer la compra y si es correcto seguir con el
		// proceso de compra
		
		return true;
	}
}
