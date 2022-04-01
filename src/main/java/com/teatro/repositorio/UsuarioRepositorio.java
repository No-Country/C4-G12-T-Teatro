package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
