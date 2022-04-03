package com.teatro.modelo;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Promocion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String titulo;

	private String urlImagen;

	private boolean activa = true;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "promocion_id"), inverseJoinColumns = @JoinColumn(name = "show_id"))
	@NonNull
	private List<Show> shows;

	public abstract boolean esNula();

	public float getPrecio() {
		return (float) shows.stream().mapToDouble(Show::getPrecio).sum();
	}

	public LocalDateTime getFechaShow() {
		return shows.get(0).getFechaShow();
	}

	public int getDuracionMinShow() {
		return shows.get(0).getDuracionMinShow();
	}

	public String descripcion() {
		return shows.get(0).getDescripcion();
	}

	public Categoria getCategoria() {
		return shows.get(0).getCategoria();
	}
}
