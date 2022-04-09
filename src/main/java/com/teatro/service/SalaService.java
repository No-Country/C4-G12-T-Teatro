package com.teatro.service;

import com.teatro.entity.Sala;

public interface SalaService {

	public Sala insertar(Sala sala);// insertar una sala

	public Sala actualizar(Sala sala);

	public Iterable<Sala> listar();// todas las salas

	public void eliminar(Sala sala);
	// public List<Sala> getAllBySala();// solo salas disponibles

}
