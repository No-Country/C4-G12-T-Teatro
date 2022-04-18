package com.teatro.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
			Optional<Integer> edad, Optional<String> preferencia, Pageable pageable) {
		return null;
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
