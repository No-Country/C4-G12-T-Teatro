package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.Modelo.PromocionPorcentual;

public interface PromoPorcRepositorio extends JpaRepository<PromocionPorcentual, Long> {

}
