package com.teatro.servicio;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Show;
import com.teatro.repositorio.ShowRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class ShowServicio extends BaseServicio<Show, Long, ShowRepositorio> {

	@Autowired
	public ShowServicio(ShowRepositorio repositorio) {
		super(repositorio);
	}

	public Page<Show> findByArgs(Optional<String> titulo, Optional<Float> precio, Optional<LocalDateTime> fechaShow,
			Optional<Integer> categoriaId, Pageable pageable) {
		
		return null;
	}

}
