package com.teatro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.teatro.entity.Sala;
import com.teatro.service.SalaService;

@RestController
@RequestMapping("/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;

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
