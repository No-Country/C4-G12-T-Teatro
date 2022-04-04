package com.teatro.Servicio;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.teatro.Modelo.Promocion;
import com.teatro.repositorio.PromocionRepositorio;

@Service
public class PromocionServicio extends BaseServicio<Promocion, Long, PromocionRepositorio> {

	@Autowired
	public PromocionServicio(PromocionRepositorio repositorio) {
		super(repositorio);
	}

	public Page<Promocion> buscarPorArgs(Optional<String> titulo, Optional<Float> precio,
			Optional<LocalDateTime> fechaShow, Optional<Integer> categoriaId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
