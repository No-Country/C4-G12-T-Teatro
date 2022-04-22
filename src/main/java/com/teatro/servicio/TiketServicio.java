package com.teatro.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Tiket;
import com.teatro.repositorio.TiketRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class TiketServicio extends BaseServicio<Tiket, Long, TiketRepositorio> {

	@Autowired
	public TiketServicio(TiketRepositorio repositorio) {
		super(repositorio);
	}
}
