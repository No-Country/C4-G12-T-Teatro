package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teatro.modelo.Tiket;

import java.io.Serializable;

@Repository
public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{

}
