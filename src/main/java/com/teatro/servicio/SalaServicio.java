package com.teatro.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Sala;

@Service
public class SalaServicio extends BaseServicio<Sala, Long, SalaRepositorio> {

	@Autowired
	public SalaServicio(SalaRepositorio salaRepo) {
		super(salaRepo);
	}
}
