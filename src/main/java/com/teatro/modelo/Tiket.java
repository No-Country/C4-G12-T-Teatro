package com.teatro.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Tiket implements Serializable {

	private static final long serialVersionUID = 7821507459652032840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_compra")
	@CreatedDate
	LocalDate local;

	@NotNull
	@Min(1)
	@Max(100000)
	private float precio;

	@NotNull
	@Size(max=100)
	@Column (name = "nombre_apellido")
	private  String nombreApellido;


	@NotNull
	@Min(1)
	@Column(name = "cantidad_entradas")
	private int cantidadEntradas;

	@ManyToOne
	@JoinColumn(name = "show_id")
	private Show show;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name= "usuario_id")
	private Usuario comprador;
	
	@OneToMany(mappedBy = "tiket")
	private List<Butaca> butacas;


	public boolean esNulo() {
		return false;
	}
}
