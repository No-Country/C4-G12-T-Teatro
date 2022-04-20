package com.teatro.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shows")
public class Show implements Serializable{

	private static final long serialVersionUID = -5984187142452274435L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 200)
	@Column(unique = true)
	private String titulo;

	@Min(0)
	@NotNull
	private float precio;

	private String urlImagen;

	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime fechaShow;

	@Min(0)
	@Max(240)
	@NotNull
	private int duracionMinShow;

	@Size(max = 1500)
	private String descripcion;

	@ManyToOne()
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "show")
	@JsonBackReference
	private List<Promocion> promociones;

	@Builder.Default
	@OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
	private List<Butaca> butacas = new ArrayList<>();

	public boolean esNulo() {
		return false;
	}

	public void agregarA(Sala sala) {
		this.sala = sala;
	}

	public boolean tieneSala() {
		return this.sala != null;
	}

	public boolean noTieneA(Sala sala) {
		return !this.sala.equals(sala);
	}

	public void eliminarSala() {
		this.sala = null;
	}
}
