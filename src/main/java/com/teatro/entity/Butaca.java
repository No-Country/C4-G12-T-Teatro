package com.teatro.entity;

import javax.persistence.*;

@Entity
@Table(name = "Butaca")
public class Butaca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "ESTADO", nullable = false)
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
}
