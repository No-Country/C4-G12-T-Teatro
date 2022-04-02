package com.teatro.Controlador;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.base.Optional;
import com.teatro.Modelo.Promocion;
import com.teatro.Servicio.PromocionServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ControladorPromocion {

	private final PromocionServicio promocionServicio;
	
	
	@GetMapping
	public ResponseEntity<List<Promocion>> obtenerPromociones(
		@RequestParam("titulo")Optional<String> titulo,
		@RequestParam("precio")Optional<Float> precio,
		@RequestParam("fechaShow")Optional<LocalDateTime> fechaShow,
		@RequestParam("categoriaId")Optional<Integer> categoriaId,
		@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request){
		
		Page<Promocion> promociones = promocionServicio.findByArgs(titulo, precio, fechaShow, categoriaId, pageable);
				
				if (promociones.isEmpty()) {
			       throw new PromocionSinResultadoException();
		        }
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		
		
		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(promociones, builder))
		                .body(promociones.getContent());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Promocion> obtenerPromocion(@PathVariable Long id){
		Promocion promo = promocionServicio.buscarporId(id).orElse(PromocionNula.construir());
		
		if (promo.esNulo()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(promo);
	}
		
	
	@PostMapping("")
	public ResponseEntity<Promocion> guardar(@Valid @RequestBody Promocion promo){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(promocionServicio.guardarPromo(promo));
	}
	
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity eliminarPromocion(@PathVariable ("id") Long id) {
		promocionServicio.eliminarPromocion(id);
		return ResponseEntity.ok(!promocionServicio.existById(id));
	}
	
	
	@PutMapping
	public ResponseEntity<Promocion> editarPromocion(@Valid @RequestBody Promocion promo ){
		return ResponseEntity.status(HttpStatus.OK).body(promocionServicio.editarPromocion(promo));
	}
	
	
	
	
		
	
}
