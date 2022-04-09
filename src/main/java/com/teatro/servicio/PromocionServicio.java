package com.teatro.servicio;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import com.teatro.dto.show.CrearPromocionDto;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.Show;
import com.teatro.modelo.objetonulo.PromocionNula;
import com.teatro.modelo.objetonulo.ShowNulo;
import com.teatro.repositorio.PromocionRepositorio;
import com.teatro.servicio.base.BaseServicio;
import com.teatro.util.converter.PromocionDtoConverter;

@Service
public class PromocionServicio extends BaseServicio<Promocion, Long, PromocionRepositorio> {

	private final PromocionDtoConverter converter;
	private final AlmacenamientoServicio almacenamientoServicio;
	private final ShowServicio showServicio;
	private final CategoriaServicio categoriaServicio;

	@Autowired
	public PromocionServicio(PromocionRepositorio repositorio, PromocionDtoConverter converter,
			AlmacenamientoServicio almacenamientoServicio, ShowServicio showServicio, CategoriaServicio categoriaServicio) {
		super(repositorio);
		this.converter = converter;
		this.almacenamientoServicio = almacenamientoServicio;
		this.showServicio = showServicio;
		this.categoriaServicio = categoriaServicio;
	}

	public Page<Promocion> buscarPorArgs(Optional<String> titulo, Optional<Float> precio, Optional<String> fechaShowString,
			Optional<String> categoriaNombre, Pageable pageable) {

		Specification<Promocion> specNombrePromocion = new Specification<Promocion>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (titulo.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No se filtra nada
				}
			}
		};

		Specification<Promocion> specPrecioMenorQue = new Specification<Promocion>() {
			private static final long serialVersionUID = 8340953606260106235L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (precio.isPresent()) {
					return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precio.get());
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Promocion> specDeCategoria = new Specification<Promocion>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (categoriaNombre.isPresent()) {
					return criteriaBuilder.equal(root.get("categoria").get("nombre"), categoriaNombre.get());
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Promocion> specFechaMayorQue = new Specification<Promocion>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				LocalDateTime fechaShow = LocalDateTime.parse(fechaShowString.get().FormateadorFecha.getFormateador());
				
				if (fechaShowString.isPresent()) {
					return criteriaBuilder.greaterThanOrEqualTo(root.get("fechaShow"), fechaShow);
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Promocion> todas = specNombrePromocion.and(specPrecioMenorQue).and(specDeCategoria).and(specFechaMayorQue);

		return this.repositorio.findAll(todas, pageable);
	}

	public Promocion guardarImagenYagregarUrlImagen(CrearPromocionDto dto, MultipartFile file) {
		String urlImagen = null;

		if (!file.isEmpty()) {
			String imagen = almacenamientoServicio.store(file);
			urlImagen = MvcUriComponentsBuilder.fromMethodName(FicheroControlador.class, "serveFile", imagen, null)
					.build().toUriString();
		}

		Promocion promocion = converter.convertirCrearPromocionDtoAPromocion(dto);
		promocion.setUrlImagen(urlImagen);

		return promocion;
	}

	public Promocion editar(Long id, CrearPromocionDto crearPromocionDto, MultipartFile file) {

		Promocion promocion = buscarPorId(id).orElse(PromocionNula.construir());

		if (!promocion.esNulo()) {
			
			switch (CrearPromocionDto.getTipo()) {
			case "porcentual": {
				
				promocion = PromocionPorcentual(crearPromocionDto.getTitulo(),crearPromocionDto.getUrlImagen,
						crearPromocionDto.getShowId().stream().map(id => servicioShow.buscarPorId(id))
						.collect(Arrays.asList()), crearPromocionDto.getDescuento());
			}
			default:
				throw new IllegalArgumentException("Valor inesperado: " + key);
			}
			
			
			promocion = Promocion.builder.titulo(crearPromocionDto.getTitulo()).precio(crearPromocionDto.getPrecio())
					.fechaShow(crearPromocionDto.getFechaShow()).duracionMinShow(crearPromocionDto.getDuracionMinShow())
					.descripcion(crearPromocionDto.getDescripcion())
					.categoria(servicioCategoria.buscarPorId(crearPromocionDto.getCategoriaId()))
					.sala(servicioSala.buscarPorId(crearPromocionDto.getSalaId()))
					.promociones(crearPromocionDto.getPromocionId().stream()
					.map(id -> servicioPromocion.buscarPorId(id))
					.collect(Arrays.asList()))
					.build();

			if (!file.isEmpty()) {
				String imagen = almacenamientoServicio.store(file);
				String urlImagen = MvcUriComponentsBuilder
						.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
				promocion.setUrlImagen(urlImagen);
			}

			return guardar(promocion);
		} else
			return promocion;
	}
}
