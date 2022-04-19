package com.teatro.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Categoria;
import com.teatro.repositorio.CategoriaRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class CategoriaServicio extends BaseServicio<Categoria, Long, CategoriaRepositorio> {

	@Autowired
	public CategoriaServicio(CategoriaRepositorio repositorio) {
		super(repositorio);
	}

}
