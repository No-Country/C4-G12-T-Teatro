package com.teatro.util.converter;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.show.CrearShowDto;
import com.teatro.dto.show.GetShowDto;
import com.teatro.modelo.Categoria;
import com.teatro.modelo.Sala;
import com.teatro.modelo.Show;
import com.teatro.servicio.CategoriaServicio;
import com.teatro.servicio.SalaServicio;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShowDtoConverter {

	private final ModelMapper mapper;
	private final CategoriaServicio categoriaServicio;
	private final SalaServicio salaServicio;

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

	public Show convertirCrearShowDtoAShowManual(CrearShowDto dto) {
		Categoria categoria = categoriaServicio.buscarPorId(dto.getCategoriaId()).get();
		Sala sala = salaServicio.buscarPorId(dto.getSalaId()).get();
		
		return new Show(null, dto.getTitulo(), dto.getPrecio(), null, LocalDateTime.from(dto.getFechaShow()),
				dto.getDuracionMinShow(), dto.getDescripcion(), categoria, sala, Arrays.asList(), Arrays.asList());
	}

	public Show convertirCrearShowDtoAShowManual(@Valid CrearShowDto dto, Show show) {
		Categoria categoria = categoriaServicio.buscarPorId(dto.getCategoriaId()).get();
		Sala sala = salaServicio.buscarPorId(dto.getSalaId()).get();
		
		show.setTitulo(dto.getTitulo());
		show.setDescripcion(dto.getDescripcion());
		show.setCategoria(categoria);
		show.setSala(sala);
		show.setDuracionMinShow(dto.getDuracionMinShow());
		show.setFechaShow(LocalDateTime.from(dto.getFechaShow()));
		show.setPrecio(dto.getPrecio());
		
		return show;
	}
}
