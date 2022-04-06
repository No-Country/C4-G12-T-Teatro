package com.teatro.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length(max = 200)
	@Column(unique = true)
	private String titulo;

	@Min(0)
	@NotNull
	private float precio;

	private String urlImagen;

	@NotNull
	private LocalDateTime fechaShow;

	@Min(0)
	@Max(240)
	@NotNull
	private int duracionMinShow;

	private String descripcion;

	@Builder.Default
	private boolean activa = true;

	@ManyToOne()
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	/*@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;*/

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shows")
	@JsonBackReference
	private List<Promocion> promociones;

	public boolean esNulo() {
		return false;
	}
}
