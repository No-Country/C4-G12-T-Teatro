package com.teatro.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teatro.modelo.Sala;
import com.teatro.servicio.SalaServicio;

@RestController
@RequestMapping("/sala")
public class SalaControlador {

	@Autowired
	private SalaServicio salaService;

	@GetMapping
	public Iterable<Sala> listar() {
		return salaService.buscarTodos();
	}

	@PostMapping
	public Sala insertar(@RequestBody Sala s) {
		return salaService.guardar(s);
	}

	@PutMapping
	public Sala actualizar(@RequestBody Sala s) {
		return salaService.guardar(s);
	}

	@DeleteMapping
	public void eliminar(@RequestBody Sala s) {
		salaService.borrar(s);
	}
}
