package com.teatro.controlador;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
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

import com.google.common.base.Optional;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.objetonulo.PromocionNula;
import com.teatro.servicio.PromocionServicio;
import com.teatro.util.paginacion.PaginacionLinks;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("promociones")
public class ControladorPromocion {

	private final PromocionServicio promocionServicio;
	private final PaginacionLinks paginacionLinks;

	@GetMapping
	public ResponseEntity<List<Promocion>> obtenerPromociones(@RequestParam("titulo") Optional<String> titulo,
			@RequestParam("precio") Optional<Float> precio,
			@RequestParam("fechaShow") Optional<LocalDateTime> fechaShow,
			@RequestParam("categoriaId") Optional<Long> categoriaId,
			@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request) {

		Page<Promocion> promociones = promocionServicio.buscarPorArgs(titulo, precio, fechaShow, categoriaId, pageable);

		if (promociones.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(promociones, builder))
				.body(promociones.getContent());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Promocion> obtenerPromocion(@PathVariable Long id) {
		Promocion promocion = promocionServicio.buscarPorId(id).orElse(PromocionNula.construir());

		if (promocion.esNulo()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(promocion);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Promocion> crearPromocion(@RequestPart("promocion") Promocion promocionDto,
			@RequestPart("imagen") MultipartFile imagen) {
		Promocion promocion = promocionServicio.guardarImagenYagregarUrlImagen(promocionDto, imagen);
		return ResponseEntity.status(HttpStatus.CREATED).body(promocionServicio.guardar(promocion));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Promocion> eliminarPromocion(@PathVariable Long id) {
		Promocion promocion = promocionServicio.buscarPorId(id).orElse(PromocionNula.construir());
		if (promocion.esNulo()) {
			return ResponseEntity.notFound().build();
		}

		promocionServicio.borrar(promocion);

		return ResponseEntity.noContent().build();
	}

	@PutMapping(name = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Promocion> editarPromocion(@PathVariable Long id,
			@RequestPart("promocion") Promocion promocionDto, @RequestPart("imagen") MultipartFile imagen) {
		Promocion promocion = promocionServicio.editar(id, promocionDto, imagen);
		if (promocion.esNulo()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(promocion);
	}

	//
}