package com.teatro.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teatro.modelo.Ticket;

public interface TicketRepositorio extends JpaRepository<Ticket, Long>{

}
