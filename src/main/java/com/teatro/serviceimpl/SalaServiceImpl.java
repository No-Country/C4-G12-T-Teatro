package com.teatro.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.entity.Sala;
import com.teatro.repository.SalaRepository;
import com.teatro.service.SalaService;

@Service
public class SalaServiceImpl implements SalaService {

	@Autowired
	private SalaRepository salaRepo;

	@Override
	public Sala insertar(Sala s) {
		return salaRepo.save(s);
	}

	@Override
	public Sala actualizar(Sala s) {
		return salaRepo.save(s);
	}

	@Override
	public Iterable<Sala> listar() {
		return salaRepo.findAll();
	}

	@Override
	public void eliminar(Sala s) {
		salaRepo.delete(s);
	}

}
