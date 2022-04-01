package com.teatro.controlador;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.teatro.UsuarioServicio;
import com.teatro.modelo.Usuario;

import lombok.RequiredArgsConstructor;

public class UsuarioControlador {
	
	@RestController
	@RequiredArgsConstructor
	@RequestMapping("/shows")
	public class ShowControlador {

		private final UsuarioServicio usuarioServicio;
		private final PaginacionLinks paginacionLinks;

		@GetMapping
		public ResponseEntity<List<Usuario>> obtenerShows(
				@RequestParam("nombre") Optional<String> nombre,
				@RequestParam("apellido") Optional<String> apellido,
				@RequestParam("email") Optional<String> email,
				@RequestParam("contraseña") Optional<String> contraseña,
				@RequestParam("urlAvatar") Optional<String> urlAvatar,
				@RequestParam("edad") Optional<Integer> edad,
				@RequestParam("fechaDeAlta") Optional<LocalDateTime> fechaDeAlta,
				@RequestParam("categoria") Optional<Categoria> categoria,
				@PageableDefault(size = 20, page = 0) Pageable pageable, HttpServletRequest request) {
			Page<Usuario> usuarios = usuarioServicio.buscarPorArgs(apellido, email, fechaDeAlta, categoria, pageable);

			if (usuarios.isEmpty()) {
				throw new UsuariosSinResultadoException();
			}

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(usuarios, builder))
					.body(usuarios.getContent());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
			Usuario usuario = usuarioServicio.buscarPorId(id).orElse(UsuarioNulo.construir());

			if (usuario.esNulo()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(usuario);
		}

		@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		public ResponseEntity<Usuario> nuevoUsuario(@RequestPart("usuario") Usuario usuario, @RequestPart("file") MultipartFile file) {

			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.nuevoShow(usuario, file));
		}

		@PutMapping("/{id}")
		public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
			Usuario usuario = UsuarioServicio.editar(id, usuario);
				
		}
		
	}

}
