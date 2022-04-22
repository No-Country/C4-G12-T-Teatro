package com.teatro.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;
import com.teatro.repositorio.TiketRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class TiketServicio extends BaseServicio<Tiket, Long, TiketRepositorio> {

	@Autowired
	public TiketServicio(TiketRepositorio repositorio) {
		super(repositorio);
	}

	public List<Tiket> buscarPorComprador(Usuario comprador) {
		return repositorio.findByComprador(comprador);
	}

	public List<Tiket> buscarPorNombre(String tiket) {
		return repositorio.findByNombreApellido(tiket);
	}
}
