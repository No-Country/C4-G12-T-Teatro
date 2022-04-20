package com.teatro.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Sala;

public interface SalaRepositorio extends JpaRepository<Sala, Long>{

	Optional<Sala> findByNombre(String nombre);

}
