package com.teatro.controlador;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.teatro.dto.usuario.EditarUsuarioDto;
import com.teatro.dto.usuario.GetUsuarioDto;
import com.teatro.dto.usuario.RegistrarUsuarioDto;
import com.teatro.error.exceptions.ContrasenasNoCoincidenException;
import com.teatro.error.exceptions.UsuarioYaExisteException;
import com.teatro.error.exceptions.ValidacionException;
import com.teatro.modelo.Usuario;
import com.teatro.modelo.objetonulo.UsuarioNulo;
import com.teatro.seguridad.JwtProveedor;
import com.teatro.servicio.UsuarioServicio;
import com.teatro.util.converter.UsuarioDtoConverter;
import com.teatro.util.enumerados.RolUsuario;
import com.teatro.util.paginacion.PaginacionLinks;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {

	private final PasswordEncoder encoder;
	private final UsuarioServicio usuarioServicio;
	private final UsuarioDtoConverter converter;
	private final PaginacionLinks paginacionLinks;
	private final JwtProveedor jwtProveedor;
	
	@GetMapping
	public ResponseEntity<List<GetUsuarioDto>> listarTodosLosUsuarios(
			@RequestParam("nombre") Optional<String> nombre,
			@RequestParam("apellido") Optional<String> apellido, 
			@RequestParam("email") Optional<String> email,
			@RequestParam("edad") Optional<Integer> edad,
			@RequestParam("preferencia") Optional<String> preferencia,
			@PageableDefault(size = 20, page = 0, direction = Direction.ASC, sort = {"nombre", "apellido"}) Pageable pageable
			, HttpServletRequest request
			){
		Page<Usuario> usuarios = usuarioServicio.buscarPorArgs(nombre, apellido, email, edad, preferencia, pageable);

		if (usuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		List<GetUsuarioDto> getUsuarioDtos = usuarios.getContent().stream()
														.map(converter::convertirUsuarioAGetUsuarioDto)
														.collect(Collectors.toList());
		
		return ResponseEntity.ok().header("link", paginacionLinks.crearLinkHeader(usuarios, builder))
				.body(getUsuarioDtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GetUsuarioDto> buscarUsuario(
			@PathVariable Long id){
		Usuario usuario = usuarioServicio.buscarPorId(id).orElse(UsuarioNulo.construir());
		
		if(usuario.esNulo()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(converter.convertirUsuarioAGetUsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GetUsuarioDto> editarUsuario(
			@PathVariable Long id,
			@RequestPart("usuario") EditarUsuarioDto editarUsuarioDto,
			Errors errores,
			@RequestPart("avatar") MultipartFile avatar){
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		Usuario usuario = usuarioServicio.buscarPorId(id).orElse(UsuarioNulo.construir());
		
		if(usuario.esNulo()) {
			ResponseEntity.notFound().build();
		}
		usuario = converter.convertirEditarUsuarioDtoAUsuario(editarUsuarioDto, usuario);
		usuario = usuarioServicio.guardarImagenYAgregarUrlImagen(usuario, avatar);
		usuarioServicio.editar(usuario);
		
		return ResponseEntity.ok(converter.convertirUsuarioAGetUsuarioDto(usuario));
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<GetUsuarioDto> registrarUsuario(
			@RequestPart("usuario") RegistrarUsuarioDto registrarUsuarioDto,
			Errors errores,
			@RequestPart("avatar") MultipartFile avatar){
		
		if(errores.hasErrors()) {
			throw new ValidacionException(errores.getAllErrors());
		}
		if (!registrarUsuarioDto.getContrasena().equals(registrarUsuarioDto.getContrasenaRepetida())) {
			throw new ContrasenasNoCoincidenException();
		}
		if (usuarioServicio.existePorEmail(registrarUsuarioDto.getEmail())) {
			throw new UsuarioYaExisteException(registrarUsuarioDto.getEmail());
		}
		Usuario usuario = converter.convertirRegistrarUsuarioDtoAUsuario(registrarUsuarioDto);
		usuario.setRoles(Arrays.asList(RolUsuario.ROLE_USER));
		usuario = usuarioServicio.guardarImagenYAgregarUrlImagen(usuario, avatar);
		usuario.setContrasena(encoder.encode(usuario.getContrasena()));
		usuarioServicio.guardar(usuario);
		String jwt = jwtProveedor.generarToken(usuario.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).header("token", jwt)
				.body(converter.convertirUsuarioAGetUsuarioDto(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GetUsuarioDto> borrarUsuario(
			@PathVariable Long id){
		Usuario usuario = usuarioServicio.buscarPorId(id).orElse(UsuarioNulo.construir());
		
		if(usuario.esNulo()) {
			return ResponseEntity.notFound().build();
		}
		usuarioServicio.borrar(usuario);
		
		return ResponseEntity.noContent().build();
	}
}
