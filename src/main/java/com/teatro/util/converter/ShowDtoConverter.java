package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.show.CrearShowDto;
import com.teatro.dto.show.GetShowDto;
import com.teatro.modelo.Show;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShowDtoConverter {

	private final ModelMapper mapper;

	public Show convertirCrearShowDtoAShow(CrearShowDto dto) {
		return mapper.map(dto, Show.class);
	}
	
	public Show convertirCrearShowDtoAShow(CrearShowDto dto, Show show) {
		mapper.map(dto, show);
		return show;
	}

	public GetShowDto convertirShowAGetShowDto(Show show) {
		return mapper.map(show, GetShowDto.class);
	}
}
