package com.teatro.repositorio;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.teatro.dto.Tiket.TiketDto;
import com.teatro.modelo.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Tiket;

public interface TiketRepositorio  extends JpaRepository<Tiket, Long> ,Serializable{
    List<Tiket> findByComprador(Usuario comprador);
    List<Tiket> findByNombreApellido(String comprado);
}
