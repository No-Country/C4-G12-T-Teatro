package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.show.CrearPromocionDto;
import com.teatro.modelo.Promocion;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PromocionDtoConverter {
	
	private final ModelMapper modelMapper;

	public Promocion convertirCrearPromocionDtoAPromocion(CrearPromocionDto dto) {
		return modelMapper.map(dto, Promocion.class);
	}

	public CrearPromocionDto convertirPromocionACrearPromocionDto(Promocion promocion) {
		return modelMapper.map(promocion, CrearPromocionDto.class);
	}
}
