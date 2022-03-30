package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Show;

public interface ShowRepositorio extends JpaRepository<Show, Long> {

}
