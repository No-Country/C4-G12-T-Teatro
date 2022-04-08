package com.teatro.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime fechaCompra;

	private String numTarjeta;

	private String bancoTarjeta;

	private int cantidad;

	private float precio;
	
	@ManyToOne
	private Usuario comprador;

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;
	
	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "ticket_id"), 
			inverseJoinColumns = @JoinColumn(name = "butaca_id"))
	private List<Butaca> butacas;

	public float getTotal() {
		return precio * cantidad;
	}
}
