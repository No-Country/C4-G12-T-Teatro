package com.teatro.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Tiket;
import com.teatro.modelo.Usuario;

public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{

    List<Tiket> findByComprador(Usuario comprador);
    List<Tiket> findByNombreApellido(String comprado);

}
