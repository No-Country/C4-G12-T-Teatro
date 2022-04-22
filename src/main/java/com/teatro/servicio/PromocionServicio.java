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
import com.teatro.modelo.Promocion;
import com.teatro.repositorio.PromocionRepositorio;
import com.teatro.servicio.base.BaseServicio;
import com.teatro.util.formateador.FormateadorFecha;

@Service
public class PromocionServicio extends BaseServicio<Promocion, Long, PromocionRepositorio> {

	private final AlmacenamientoServicio almacenamientoServicio;

	@Autowired
	public PromocionServicio(PromocionRepositorio repositorio, AlmacenamientoServicio almacenamientoServicio) {
		super(repositorio);
		this.almacenamientoServicio = almacenamientoServicio;
	}


	public Page<Promocion> buscarPorArgs(Optional<String> titulo, Optional<String> fechaShowString,
			Optional<String> categoriaNombre, Pageable pageable) {

		Specification<Promocion> specNombreShow = new Specification<Promocion>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				if (titulo.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No se filtra nada
				}
			}
		};

		Specification<Promocion> specDeCategoria = new Specification<Promocion>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				if (categoriaNombre.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("categoria").get("nombre")),
							"%" + categoriaNombre.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Promocion> specFechaMayorQue = new Specification<Promocion>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Promocion> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				if (fechaShowString.isPresent()) {
					LocalDateTime fechaShow = LocalDateTime.parse(fechaShowString.get(), FormateadorFecha.getFormateador());
					return criteriaBuilder.greaterThanOrEqualTo(root.get("fechaShow"), fechaShow);
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Promocion> todas = specNombreShow.and(specDeCategoria).and(specFechaMayorQue);

		return this.repositorio.findAll(todas, pageable);
	}
//
//<<<<<<< HEAD
//	public Promocion guardarImagenYagregarUrlImagen(CrearPromocionDto promocionDto, MultipartFile file) {
//		String urlImagen = null;
//
//		if (!file.isEmpty()) {
//			String imagen = almacenamientoServicio.store(file);
//			urlImagen = MvcUriComponentsBuilder.fromMethodName(FicheroControlador.class, "serveFile", imagen, null)
//					.build().toUriString();
//		}
//
//		Promocion promocion = converter.convertirCrearPromocionDtoAPromocion(promocionDto);
//		promocion.setUrlImagen(urlImagen);
//
//		return promocion;
//	}

//	public Promocion editar(Long id, CrearPromocionDto crearPromocionDto, MultipartFile file) {

//		Promocion promocion = buscarPorId(id).orElse(PromocionNula.construir());

//		if (!promocion.esNula()) {
//
//			switch (CrearPromocionDto.getTipo()) {
//			case "porcentual": {
//
//				promocion = PromocionPorcentual(crearPromocionDto.getTitulo(),crearPromocionDto.getUrlImagen,
//						crearPromocionDto.getShowId().stream().map(id => servicioShow.buscarPorId(id))
//						.collect(Arrays.asList()), crearPromocionDto.getDescuento());
//			}
//			default:
//				throw new IllegalArgumentException("Valor inesperado: " + key);
//			}
//
//			if (!file.isEmpty()) {
//				String imagen = almacenamientoServicio.store(file);
//				String urlImagen = MvcUriComponentsBuilder
//						.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
//				promocion.setUrlImagen(urlImagen);
//			}
//
//			return guardar(promocion);
//		} else
//			return promocion;
//	}

	public Promocion guardarImagenYagregarUrlImagen(Promocion promocion, MultipartFile archivo) {
		if (!archivo.isEmpty()) {
			String imagen = almacenamientoServicio.store(archivo);
			String urlImagen = MvcUriComponentsBuilder
					.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
			
			if(promocion.getUrlImagen() != null) {
				almacenamientoServicio.delete(promocion.getUrlImagen());
			}
			promocion.setUrlImagen(urlImagen);
		}
		return promocion;
	}

}
