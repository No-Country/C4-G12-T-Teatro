package com.teatro.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teatro.modelo.Ticket;
import com.teatro.repositorio.TicketRepositorio;
import com.teatro.servicio.base.BaseServicio;

@Service
public class TicketServicio extends BaseServicio<Ticket, Long, TicketRepositorio> {

	@Autowired
	public TicketServicio(TicketRepositorio repositorio) {
		super(repositorio);
	}

}
