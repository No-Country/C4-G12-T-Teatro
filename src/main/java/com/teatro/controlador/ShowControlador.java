package com.teatro.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.teatro.modelo.Show;
import com.teatro.servicio.ShowServicio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shows")
public class ShowControlador {

	private final ShowServicio showServicio;
	private final PaginacionLinks paginacionLinks;
	
	@GetMapping
	public ResponseEntity<List<Show>> getShows(
			@RequestParam("titulo") Optional<String> titulo,
			@RequestParam("precio") Optional<Float> precio,
			@RequestParam("fechaShow") Optional<LocalDateTime> fechaShow,
			@RequestParam("categoriaId") Optional<Integer> categoriaId,
			@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request){
		
		Page<Show> shows = showServicio.findByArgs(titulo, precio, fechaShow, categoriaId, pageable);
		
		if (shows.isEmpty()) {
			throw new BuscarShowSinResultadoException();
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(shows, builder))
				.body(shows.getContent());
		
	}
	
}
