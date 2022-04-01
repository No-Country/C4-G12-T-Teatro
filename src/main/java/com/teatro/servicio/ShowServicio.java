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

import com.martiniriarte.controlador.FicheroControlador;
import com.martiniriarte.modelo.Producto;
import com.teatro.dto.show.CrearShowDto;
import com.teatro.modelo.Show;
import com.teatro.modelo.objetonulo.ShowNulo;
import com.teatro.repositorio.ShowRepositorio;
import com.teatro.servicio.base.BaseServicio;

import util.converter.ShowDtoConverter;

@Service
public class ShowServicio extends BaseServicio<Show, Long, ShowRepositorio> {

	private final ShowDtoConverter converter;
	private final AlmacenamientoServicio almacenamientoServicio;

	@Autowired
	public ShowServicio(ShowRepositorio repositorio, ShowDtoConverter converter,
			AlmacenamientoServicio almacenamientoServicio) {
		super(repositorio);
		this.converter = converter;
		this.almacenamientoServicio = almacenamientoServicio;
	}

	public Page<Show> buscarPorArgs(Optional<String> titulo, Optional<Float> precio, Optional<LocalDateTime> fechaShow,
			Optional<Integer> categoriaId, Pageable pageable) {

		Specification<Show> specNombreProducto = new Specification<Show>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Show> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (titulo.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No se filtra nada
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

		Specification<Show> ambas = specNombreProducto.and(specPrecioMenorQue);

		return this.repositorio.findAll(ambas, pageable);
	}

	public Object nuevoShow(CrearShowDto dto, MultipartFile file) {
		String urlImagen = null;

		if (!file.isEmpty()) {
			String imagen = almacenamientoServicio.store(file);
			urlImagen = MvcUriComponentsBuilder.fromMethodName(FicheroControlador.class, "serveFile", imagen, null)
					.build().toUriString();
		}
		
		Show show = converter.convertirCrearShowDtoAShow(dto);
		show.setUrlImagen(urlImagen);

		return converter.convertirProductoAProductoDto(guardar(producto));
	}

	public Show editar(Long id, CrearShowDto crearShowDto, MultipartFile file) {
		Show show = buscarPorId(id).orElse(ShowNulo.construir());

		if (!show.esNulo()) {
			if (!file.isEmpty()) {
				String imagen = almacenamientoServicio.store(file);
				String urlImagen = MvcUriComponentsBuilder
						.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
				show.setUrlImagen(urlImagen);
			}

			return guardar(show);
		} else
			return show;
	}

}
