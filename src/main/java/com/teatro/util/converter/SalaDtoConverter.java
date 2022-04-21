package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.sala.CrearSalaDto;
import com.teatro.dto.sala.GetSalaDto;
import com.teatro.modelo.Sala;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalaDtoConverter {

	private final ModelMapper mapper;
	
	public Sala convertirCrearSalaDtoASala(CrearSalaDto salaDto) {
		return mapper.map(salaDto, Sala.class);
	}
	
	public GetSalaDto convertirSalaAGetSalaDto(Sala sala) {
		return mapper.map(sala, GetSalaDto.class);
	}
}
