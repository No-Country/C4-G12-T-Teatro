package com.teatro.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Butaca;
import com.teatro.repositorio.ButacaRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class ButacaServicio extends BaseServicio<Butaca, Long, ButacaRepositorio> {

	@Autowired
	public ButacaServicio(ButacaRepositorio repositorio) {
		super(repositorio);
	}
	
}
