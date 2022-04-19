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


}
