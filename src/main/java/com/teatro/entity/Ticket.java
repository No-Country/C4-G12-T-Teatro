package com.teatro.entity;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ticket")
public class Ticket {
	/* COLUMNAS DE LA BDD */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Getter
	@Setter
	private long id;
	@Column(name = "COSTO", nullable = false)
	@Getter
	@Setter
	private float precio;

	@OneToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;

	@OneToOne
	@JoinColumn(name = "show_id")
	private Show show;

	@OneToMany(mappedBy = "ticket")
	private Set<Butaca> buataca;

	@OneToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", precio=" + precio + ", compra=" + compra + ", show=" + show + ", buataca="
				+ buataca + ", sala=" + sala + "]";
	}

}
