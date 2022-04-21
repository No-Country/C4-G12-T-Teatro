package com.teatro.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.teatro.dto.sala.CrearSalaDto;
import com.teatro.dto.sala.EditarSalaDto;
import com.teatro.dto.sala.GetSalaDto;
import com.teatro.error.exceptions.ShowNoEncontradoException;
import com.teatro.error.exceptions.ShowYaTieneUnaSalaException;
import com.teatro.error.exceptions.ValidacionException;
import com.teatro.modelo.Sala;
import com.teatro.modelo.Show;
import com.teatro.modelo.objetonulo.SalaNula;
import com.teatro.servicio.SalaServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.util.converter.SalaDtoConverter;
import com.teatro.util.paginacion.PaginacionLinks;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaControlador {

	private final SalaServicio salaService;
	private final PaginacionLinks paginacionLinks;
	private final SalaDtoConverter converter;
	private final ShowServicio showServicio;

	@GetMapping
	public ResponseEntity<List<Sala>> listar(
			@PageableDefault(page = 0, size = 50)Pageable pageable, HttpServletRequest request) {
		Page<Sala> salas = salaService.buscarTodos(pageable);
		
		if(salas.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		
		return ResponseEntity.status(HttpStatus.OK).header("link", paginacionLinks.crearLinkHeader(salas, builder))
				.body(salas.getContent());
	}
	
	@GetMapping("/{nombre}")
	public ResponseEntity<GetSalaDto> obtenerSala(
			@PathVariable String nombre){
		Sala sala = salaService.buscarPorNombre(nombre).orElse(SalaNula.construir());
		
		if(sala.esNula()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(converter.convertirSalaAGetSalaDto(sala));
	}

	@PostMapping
	public ResponseEntity<Sala> insertar(
			@Valid @RequestBody CrearSalaDto salaDto,
			Errors errores) {
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		Sala sala = converter.convertirCrearSalaDtoASala(salaDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(salaService.guardar(sala));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sala> actualizar(
			@Valid @RequestBody EditarSalaDto salaDto,
			Errors errores,
			@PathVariable Long id) {
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		Sala sala = salaService.buscarPorId(id).orElse(SalaNula.construir());
		
		if(sala.esNula()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(salaService.editar(sala));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Sala> eliminar(
			@PathVariable Long id) {
		Sala sala = salaService.buscarPorId(id).orElse(SalaNula.construir());
		
		if(sala.esNula()) {
			return ResponseEntity.notFound().build();
		}
		salaService.borrar(sala);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{nombreSala}/show/{idShow}")
	public ResponseEntity<Sala> agregarShowASala(
			@PathVariable String nombreSala,
			@PathVariable Long idShow){
		Sala sala = salaService.buscarPorNombre(nombreSala).orElseThrow();
		Show show = showServicio.buscarPorId(idShow).orElseThrow();
		
		if(show.tieneSala()) {
			throw new ShowYaTieneUnaSalaException(show.getTitulo());
		}
		show.agregarA(sala);
		showServicio.guardar(show);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sala);
	}
	
	@DeleteMapping("/{nombreSala}/show/{idShow}")
	public ResponseEntity<Show> eliminarShowASala(
			@PathVariable String nombreSala,
			@PathVariable Long idShow){
		Sala sala = salaService.buscarPorNombre(nombreSala).orElseThrow();
		Show show = showServicio.buscarPorId(idShow).orElseThrow(() -> new ShowNoEncontradoException(idShow));
		
		if(show.noTieneA(sala)) {
			throw new ShowYaTieneUnaSalaException(show.getTitulo());
		}
		show.eliminarSala();
		showServicio.guardar(show);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(show);
	}
}
