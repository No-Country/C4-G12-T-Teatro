package com.teatro.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Compra")
public class Compra {
	/* CAMPOS DE LA BDD */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "TOTAL_TICKETS", nullable = false)
	private int total_ticket;
	@Column(name = "SUMA_TOTAL", nullable = false)
	private float total_costo;
	@Column(name = "FECHA_COMPRA", nullable = false) // hora del show
	// @Temporal(TemporalType.DATE)
	private Date fecha_compra;
	
	@OneToOne(mappedBy = "compra")
    private Ticket ticket;
	
}
