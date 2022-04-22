package com.teatro.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.teatro.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);


}
