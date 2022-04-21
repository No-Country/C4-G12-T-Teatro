package com.teatro.servicio;

import com.teatro.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Tiket;
import com.teatro.repositorio.TiketRepositorio;
import com.teatro.servicio.base.BaseServicio;

import java.util.List;
import java.util.Optional;

@Service
public class TiketServicio extends BaseServicio<Tiket, Long, TiketRepositorio> {


	@Autowired
	public TiketServicio(TiketRepositorio repositorio) {
		super(repositorio);
	}


	public List<Tiket> buscarPorComprador(Usuario comprador) {
		return repositorio.findByComprador(comprador);
	}


	public List<Tiket> buscarPorNombre(Usuario nombre) {
		return repositorio.findByNombre(nombre);
	}
}
