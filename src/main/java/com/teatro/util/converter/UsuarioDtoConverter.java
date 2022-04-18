package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.usuario.EditarUsuarioDto;
import com.teatro.dto.usuario.GetUsuarioDto;
import com.teatro.dto.usuario.RegistrarUsuarioDto;
import com.teatro.modelo.Usuario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioDtoConverter {
	
	private final ModelMapper mapper;
	
	public GetUsuarioDto convertirUsuarioAGetUsuarioDto(Usuario usuario) {
		return mapper.map(usuario, GetUsuarioDto.class);
	}

	public Usuario convertirEditarUsuarioDtoAUsuario(EditarUsuarioDto editarUsuarioDto, Usuario usuario) {
		mapper.map(editarUsuarioDto, usuario);
		return usuario;
	}

	public Usuario convertirRegistrarUsuarioDtoAUsuario(RegistrarUsuarioDto registrarUsuarioDto) {
		return mapper.map(registrarUsuarioDto, Usuario.class);
	}
}
