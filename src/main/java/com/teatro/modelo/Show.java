package com.teatro.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show implements Ofertable{

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
	private int duracionMinShow;

	private String descripcion;

	private boolean esActiva = true;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "sala_id")
	private Sala sala;

	@Override
	public boolean esPromocion() {
		return false;
	}

}
