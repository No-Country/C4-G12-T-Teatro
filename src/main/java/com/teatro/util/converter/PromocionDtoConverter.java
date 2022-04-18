package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.show.CrearPromocionDto;
import com.teatro.modelo.Promocion;
import com.teatro.modelo.PromocionPorcentual;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PromocionDtoConverter {

	private final ModelMapper mapper;
	
	public Promocion convertirCrearPromocionDtoAPromocion(CrearPromocionDto promocionDto) {
		return mapper.map(promocionDto, PromocionPorcentual.class);
	}
}
