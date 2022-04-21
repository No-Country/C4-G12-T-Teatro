package com.teatro.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Butaca implements Serializable{

	private static final long serialVersionUID = -4082070364081833635L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int fila;
	
	private int numero;

	private boolean ocupada = false;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "show_id")
	@NotNull
	private Show show;

	@ManyToOne
	private Tiket tiket;

	@ManyToOne
	@JoinColumn(name = "seccion_id")
	private Seccion seccion;
	
	public void ocuparButaca() {
		this.ocupada = true;
	}
}

