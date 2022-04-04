package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.teatro.modelo.Promocion;

public interface PromocionRepositorio extends JpaRepository<Promocion, Long>, JpaSpecificationExecutor<Promocion> {

}
