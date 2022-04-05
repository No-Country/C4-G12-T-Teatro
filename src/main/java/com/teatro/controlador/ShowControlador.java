package com.teatro.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.teatro.dto.show.CrearShowDto;
import com.teatro.modelo.Show;
import com.teatro.modelo.objetonulo.ShowNulo;
import com.teatro.servicio.ShowServicio;
import com.teatro.util.paginacion.PaginacionLinks;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowControlador {

	private final ShowServicio showServicio;
	private final PaginacionLinks paginacionLinks;

	@GetMapping
	public ResponseEntity<List<Show>> obtenerShows(@RequestParam("titulo") Optional<String> titulo,
			@RequestParam("precio") Optional<Float> precio,
			@RequestParam("fechaShow") Optional<LocalDateTime> fechaShow,
			@RequestParam("categoriaId") Optional<Long> categoriaId,
			@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request) {
		Page<Show> shows = showServicio.buscarPorArgs(titulo, precio, fechaShow, categoriaId, pageable);

		if (shows.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(shows, builder))
				.body(shows.getContent());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Show> obtenerShow(@PathVariable Long id) {
		Show show = showServicio.buscarPorId(id).orElse(ShowNulo.construir());

		if (show.esNulo())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(show);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Show> nuevoShow(@RequestPart("show") CrearShowDto crearShowDto,
			@RequestPart("file") MultipartFile file) {
		Show show = showServicio.guardarImagenYAgregarUrlImagen(crearShowDto, file);

		return ResponseEntity.status(HttpStatus.CREATED).body(showServicio.guardar(show));
	}

	@PutMapping(name = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Show> editarShow(@PathVariable Long id, @RequestPart(name = "show") CrearShowDto crearShowDto,
			@RequestPart("file") MultipartFile file) {
		Show show = showServicio.editar(id, crearShowDto, file);

		if (show.esNulo())
			return ResponseEntity.notFound().build();
		else {
			return ResponseEntity.ok(show);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Show> borrarShow(@PathVariable Long id) {
		Show show = showServicio.buscarPorId(id).orElse(ShowNulo.construir());

		if (show.esNulo())
			return ResponseEntity.notFound().build();
		else {
			showServicio.borrar(show);
			return ResponseEntity.noContent().build();
		}
	}
}
