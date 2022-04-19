package com.teatro.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Size(max = 60)
	@Column(unique = true)
	private String titulo;

	private String urlImagen;

	@ManyToOne
	@NotNull
	@JsonManagedReference
	private Show show;

	public abstract boolean esNula();

	public String getCategoria() {
		return show.getCategoria().getNombre();
	}

	public boolean contieneA(Show show) {
		return this.show.equals(show);
	}

	public void agregarA(Show show) {
		this.show = show;
	}

	public float getPrecio() {
		return this.show.getPrecio();
	}
}
