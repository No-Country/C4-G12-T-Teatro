package com.teatro.repositorio;

import java.io.Serializable;
import java.util.List;

import com.teatro.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Tiket;

public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{


    List<Tiket> findByComprado(Long comprador);



}
