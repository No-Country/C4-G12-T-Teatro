package com.teatro.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Promocion implements Serializable{

	private static final long serialVersionUID = -1360819303963251873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String titulo;

	private String urlImagen;
    
	private boolean activa = true;

	@ManyToMany()
	@JoinTable(
			joinColumns = @JoinColumn(name = "promocion_id"), 
			inverseJoinColumns = @JoinColumn(name = "show_id"))
	@NotNull
	@JsonManagedReference
	private List<Show> shows;

	public abstract boolean esNula();

	public int getDuracionMinShow() {
		return shows.get(0).getDuracionMinShow();
	}

	public LocalDateTime getFechaShow() {
		return shows.get(0).getFechaShow();
	}

	public float getPrecio() {
		return (float) shows.stream().mapToDouble(Show::getPrecio).sum();
	}

	public String getDescripcion() {
		return shows.get(0).getDescripcion();
	}

	public String getCategoria() {
		return shows.get(0).getCategoria().getNombre();
	}
}
