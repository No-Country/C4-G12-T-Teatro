package com.teatro.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Tiket;

public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{

}
