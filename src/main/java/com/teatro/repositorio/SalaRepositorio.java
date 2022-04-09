package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teatro.modelo.Sala;

@Repository
public interface SalaRepositorio extends JpaRepository<Sala, Long> {

}
