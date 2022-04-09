package com.teatro.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teatro.modelo.Sala;
import com.teatro.servicio.SalaServicio;

@RestController
@RequestMapping("/sala")
public class SalaControlador {

	@Autowired
	private SalaServicio salaService;

	@GetMapping
	public Iterable<Sala> listar() {
		return salaService.listar();
	}

	@PostMapping
	public Sala insertar(@RequestBody Sala s) {
		return salaService.insertar(s);
	}

	@PutMapping
	public Sala actualizar(@RequestBody Sala s) {
		return salaService.actualizar(s);
	}

	@DeleteMapping
	public void eliminar(@RequestBody Sala s) {
		salaService.eliminar(s);
	}
}
