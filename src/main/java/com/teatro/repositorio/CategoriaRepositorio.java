package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{

}
