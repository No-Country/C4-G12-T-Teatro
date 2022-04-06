package com.teatro.servicio;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teatro.modelo.Promocion;
import com.teatro.repositorio.PromocionRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class PromocionServicio extends BaseServicio<Promocion , Long , PromocionRepositorio>{

	@Autowired
	public PromocionServicio(PromocionRepositorio repositorio) {
		super(repositorio);
	}

	public Page<Promocion> buscarPorArgs(Optional<String> titulo, Optional<Float> precio,
			Optional<LocalDateTime> fechaShow, Optional<Long> categoriaId, Pageable pageable) {
		return null;
	}
	public Promocion guardarImagenYagregarUrlImagen(Promocion promocionDto, MultipartFile imagen) {
		return null;
	}

	public Promocion editar(Long id, Promocion promocionDto, MultipartFile imagen) {

		return null;
	}
}
