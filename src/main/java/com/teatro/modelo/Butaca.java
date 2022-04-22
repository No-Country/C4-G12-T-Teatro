package com.teatro.modelo;

import java.io.Serializable;
import java.util.Objects;

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

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "show_id")
	@NotNull
	private Show show;

	@JsonBackReference
	@ManyToOne
	private Tiket tiket;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Butaca other = (Butaca) obj;
		return fila == other.fila && numero == other.numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fila, numero);
	}
}

