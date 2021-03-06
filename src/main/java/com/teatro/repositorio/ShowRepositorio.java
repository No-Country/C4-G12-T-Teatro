package com.teatro.repositorio;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.teatro.modelo.Sala;
import com.teatro.modelo.Show;

public interface ShowRepositorio extends JpaRepository<Show, Long>, JpaSpecificationExecutor<Show>{

	Page<Show> findAll(Specification<Show> specification, Pageable pageable);
	
	boolean existsByFechaShowBetweenAndSala(LocalDateTime desde,  LocalDateTime hasta, Sala sala);
	
	boolean existsByFechaShowBetweenAndSalaAndIdNot(LocalDateTime desde,  LocalDateTime hasta, Sala sala, Long id);
}
