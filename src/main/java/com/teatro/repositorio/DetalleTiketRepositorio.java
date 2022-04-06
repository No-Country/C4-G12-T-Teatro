package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teatro.modelo.DetalleTiket;

@Repository
public interface DetalleTiketRepositorio extends JpaRepository<DetalleTiket,Long> {

}
