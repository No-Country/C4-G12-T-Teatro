package com.teatro.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.promocion.CrearPromocionDto;
import com.teatro.dto.promocion.EditarPromocionDto;
import com.teatro.dto.promocion.GetPromocionDto;
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

	public Promocion convertirEditarPromocionDtoAPromocion(EditarPromocionDto promocionDto, Promocion promocion) {
		mapper.map(promocionDto, promocion);
		return promocion;
	}
	
	public GetPromocionDto convertirPromocionAGetPromocionDto(Promocion promocion) {
		return mapper.map(promocion, GetPromocionDto.class);
	}
}
