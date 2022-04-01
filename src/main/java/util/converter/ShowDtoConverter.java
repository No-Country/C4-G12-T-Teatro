package util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.teatro.dto.show.CrearShowDto;
import com.teatro.modelo.Show;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShowDtoConverter {

	private final ModelMapper modelMapper;

	public Show convertirCrearShowDtoAShow(CrearShowDto dto) {
		return modelMapper.map(dto, Show.class);
	}

	public CrearShowDto convertirShowACrearShowDto(Show show) {
		return modelMapper.map(show, CrearShowDto.class);
	}
}
