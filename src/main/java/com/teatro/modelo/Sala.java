package com.teatro.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Min(1)
	@Max(5000)
	@NotNull
	private int capacidad;
	@Length(min = 1, max = 50)
	private String descripcion;
	@NotNull
	private boolean estado = true;

	@OneToMany(mappedBy = "sala")
	private Show show;

	@OneToMany(mappedBy = "sala")
	private Set<Butaca> butaca;

	@OneToMany(mappedBy = "sala")
	private Ticket ticket;

}
