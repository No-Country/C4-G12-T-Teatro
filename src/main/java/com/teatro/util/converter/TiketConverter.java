package com.teatro.util.converter;

import com.teatro.dto.Tiket.TiketDto;
import com.teatro.dto.show.GetShowDto;
import com.teatro.modelo.Show;
import com.teatro.modelo.Tiket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;@Component
@RequiredArgsConstructor
public class TiketConverter {
    ModelMapper modelMapper = new ModelMapper();
    public TiketDto traerTiketsDTO(Tiket tiket)
    {
      return   modelMapper.map(tiket,TiketDto.class);
    }
}
