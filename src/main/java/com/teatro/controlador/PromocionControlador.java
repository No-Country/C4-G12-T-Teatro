package com.teatro.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

import com.teatro.dto.promocion.CrearPromocionDto;
import com.teatro.dto.promocion.GetPromocionDto;
import com.teatro.error.exceptions.PromocionNoEncontradaException;
import com.teatro.error.exceptions.PromocionYaTieneAShowException;
import com.teatro.error.exceptions.ShowNoEncontradoException;
import com.teatro.error.exceptions.ValidacionException;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Show;
import com.teatro.modelo.objetonulo.PromocionNula;
import com.teatro.servicio.PromocionServicio;
import com.teatro.servicio.ShowServicio;
import com.teatro.util.converter.PromocionDtoConverter;
import com.teatro.util.paginacion.PaginacionLinks;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promociones")
public class PromocionControlador {

	private final PromocionServicio promocionServicio;
	private final PaginacionLinks paginacionLinks;
	private final PromocionDtoConverter converter;
	private final ShowServicio showServicio;

	@GetMapping
	public ResponseEntity<List<GetPromocionDto>> obtenerPromociones(
			@RequestParam("titulo") Optional<String> titulo,
			@RequestParam("fecha") Optional<String> fechaShow,
			@RequestParam("categoria") Optional<String> categoriaNombre,
			@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request) {
		Page<Promocion> promociones = promocionServicio.buscarPorArgs(titulo, fechaShow, categoriaNombre, pageable);

		if (promociones.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		List<GetPromocionDto> getPromocionDtos = promociones.getContent().stream()
															.map(converter::convertirPromocionAGetPromocionDto)
															.collect(Collectors.toList());
		
		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(promociones, builder))
				.body(getPromocionDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Promocion> obtenerPromocion(
			@PathVariable Long id) {
		Promocion promocion = promocionServicio.buscarPorId(id).orElse(PromocionNula.construir());

		if (promocion.esNula()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(promocion);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Promocion> crearPromocion(
			@RequestPart("promocion") CrearPromocionDto promocionDto,
			Errors errores,
			@RequestPart("imagen") MultipartFile imagen) {
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		Promocion promocion = converter.convertirCrearPromocionDtoAPromocion(promocionDto);
		promocion = promocionServicio.guardarImagenYagregarUrlImagen(promocion, imagen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(promocionServicio.guardar(promocion));
	}
	
	@PutMapping(name = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Promocion> editarPromocion(
			@PathVariable Long id,
			@RequestPart("promocion") CrearPromocionDto promocionDto, 
			Errors errores,
			@RequestPart("imagen") MultipartFile imagen) {
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		Promocion promocion = promocionServicio.buscarPorId(id).orElse(PromocionNula.construir());
		
		if (promocion.esNula()) {
			return ResponseEntity.notFound().build();
		}
		promocion = promocionServicio.guardarImagenYagregarUrlImagen(promocion, imagen);
		
		return ResponseEntity.ok().body(promocionServicio.editar(promocion));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Promocion> eliminarPromocion(
			@PathVariable Long id) {
		Promocion promocion = promocionServicio.buscarPorId(id).orElse(PromocionNula.construir());
		if (promocion.esNula()) {
			return ResponseEntity.notFound().build();
		}
		promocionServicio.borrar(promocion);

		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{idPromocion}/shows/{idShow}")
	public ResponseEntity<GetPromocionDto> agregarShowAPromocion(
			@PathVariable Long idPromocion,
			@PathVariable Long idShow){
		Promocion promocion = promocionServicio.buscarPorId(idPromocion).orElseThrow(() -> new PromocionNoEncontradaException(idPromocion));
		Show show = showServicio.buscarPorId(idShow).orElseThrow(() -> new ShowNoEncontradoException(idShow));
		
		if(promocion.contieneA(show)) {
			throw new PromocionYaTieneAShowException(show.getTitulo());
		}
		promocion.agregarA(show);
		promocionServicio.guardar(promocion);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertirPromocionAGetPromocionDto(promocion));
	}
}
