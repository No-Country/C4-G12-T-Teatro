package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.tiket.TiketDto;
import com.teatro.modelo.Tiket;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TiketDtoConverter {

	private final ModelMapper mapper;
	
	public TiketDto convertirTiketATiketDto(Tiket tiket) {
		return mapper.map(tiket, TiketDto.class);
	}
}
