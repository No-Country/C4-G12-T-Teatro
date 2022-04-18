package com.teatro.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Usuario;
import com.teatro.repositorio.UsuarioRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class UsuarioServicio  extends BaseServicio<Usuario, Long, UsuarioRepositorio>{


	@Autowired
	public UsuarioServicio(UsuarioRepositorio repositorio) {
		super(repositorio);
	}

	public Optional<Usuario> buscarPorEmail(String email) {
		return this.repositorio.findByEmail(email);
	}
}
