package com.teatro.repositorio;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.teatro.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Tiket;

public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{
    List<Tiket> findByComprador(Optional<Usuario> comprador);
}
