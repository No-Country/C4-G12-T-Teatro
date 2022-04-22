package com.teatro.servicio;

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
import com.teatro.modelo.Usuario;
import com.teatro.repositorio.UsuarioRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class UsuarioServicio  extends BaseServicio<Usuario, Long, UsuarioRepositorio>{

	private final AlmacenamientoServicio almacenamientoServicio;
	
	@Autowired
	public UsuarioServicio(UsuarioRepositorio repositorio, AlmacenamientoServicio almacenamientoServicio) {
		super(repositorio);
		this.almacenamientoServicio = almacenamientoServicio;
	}

	public Optional<Usuario> buscarPorEmail(String email) {
		return this.repositorio.findByEmail(email);
	}

	public Page<Usuario> buscarPorArgs(Optional<String> nombre, Optional<String> apellido, Optional<String> email,
			Optional<Integer> edad, Optional<String> preferenciaNombre, Pageable pageable) {
		
		Specification<Usuario> specNombreUsuario = new Specification<Usuario>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (nombre.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "%" + nombre.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};
		
		Specification<Usuario> specApellidoUsuario = new Specification<Usuario>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (apellido.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("apellido")), "%" + apellido.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};
		
		Specification<Usuario> specEmailUsuario = new Specification<Usuario>() {
			private static final long serialVersionUID = 6914475554810295752L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (email.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Usuario> specEdadMenorQue = new Specification<Usuario>() {
			private static final long serialVersionUID = 8340953606260106235L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (edad.isPresent()) {
					return criteriaBuilder.lessThanOrEqualTo(root.get("edad"), edad.get());
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Usuario> specPreferencia = new Specification<Usuario>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (preferenciaNombre.isPresent()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("preferencia").get("nombre")), "%" + preferenciaNombre.get() + "%");
				} else {
					return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
				}
			}
		};

		Specification<Usuario> todas = specNombreUsuario.and(specApellidoUsuario).and(specEmailUsuario).and(specEdadMenorQue).and(specPreferencia);

		return this.repositorio.findAll(todas, pageable);
	}

	public Usuario guardarImagenYAgregarUrlImagen(Usuario usuario, MultipartFile archivo) {
		
		if (!archivo.isEmpty()) {
			String imagen = almacenamientoServicio.store(archivo);
			String urlImagen = MvcUriComponentsBuilder
					.fromMethodName(FicheroControlador.class, "serveFile", imagen, null).build().toUriString();
			
			if(usuario.getUrlAvatar() != null) {
				almacenamientoServicio.delete(usuario.getUrlAvatar());
			}
			usuario.setUrlAvatar(urlImagen);
		}
		return usuario;
	}

	public boolean existePorEmail(String email) {
		return this.repositorio.existsByEmail(email);
	}


}
