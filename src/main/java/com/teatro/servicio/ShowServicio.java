package com.teatro.servicio;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.teatro.controlador.FicheroControlador;
import com.teatro.modelo.Sala;
import com.teatro.modelo.Show;
import com.teatro.repositorio.ShowRepositorio;
import com.teatro.servicio.base.BaseServicio;
import com.teatro.util.formateador.FormateadorFecha;

@Service
public class ShowServicio extends BaseServicio<Show, Long, ShowRepositorio> {

	private final AlmacenamientoServicio almacenamientoServicio;

	@Autowired
	public ShowServicio(ShowRepositorio repositorio, AlmacenamientoServicio almacenamientoServicio) {
		super(repositorio);
		this.almacenamientoServicio = almacenamientoServicio;
	}

	public Page<Show> buscarPorArgs(Optional<String> titulo, Optional<Float> precio, Optional<String> fechaShowString,
			Optional<String> categoriaNombre, Pageable pageable) {

		Specification<Show> specNombreShow = new Specification<Show>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Show> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (titulo.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Show> specPrecioMenorQue = new Specification<Show>() {
			private static final long serialVersionUID = 8340953606260106235L;

			@Override
			public Predicate toPredicate(Root<Show> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (precio.isPresent()) {
					return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precio.get());
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Show> specDeCategoria = new Specification<Show>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Show> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (categoriaNombre.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("categoria").get("nombre")), "%" + categoriaNombre.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Show> specFechaMayorQue = new Specification<Show>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Show> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (fechaShowString.isPresent()) {
					LocalDateTime fechaShow = LocalDateTime.parse(fechaShowString.get(),FormateadorFecha.getFormateador());
					return criteriaBuilder.greaterThanOrEqualTo(root.get("fechaShow"), fechaShow);
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};
		
		Specification<Show> todas = specNombreShow.and(specPrecioMenorQue).and(specDeCategoria).and(specFechaMayorQue);
		return this.repositorio.findAll(todas, pageable);
	}

	public Show guardarImagenYAgregarUrlImagen(Show show, MultipartFile archivo) {
		if (!archivo.isEmpty()) {
			String imagen = almacenamientoServicio.store(archivo);
			String urlImagen = MvcUriComponentsBuilder
					.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
			
			if(show.getUrlImagen() != null) {
				almacenamientoServicio.delete(show.getUrlImagen());
			}
			show.setUrlImagen(urlImagen);
		}
		return show;
	}
	
	public boolean tieneLaSalaShowEntreHorarios(Sala sala, LocalDateTime desde, LocalDateTime hasta) {
		return this.repositorio.existsByFechaShowBetweenAndSala(desde, hasta, sala);
	}
	
	public boolean tieneLaSalaShowEntreHorarios(Sala sala, LocalDateTime desde, LocalDateTime hasta, Long id) {
		return this.repositorio.existsByFechaShowBetweenAndSalaAndIdNot(desde, hasta, sala, id);
	}
}
