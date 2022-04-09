package com.teatro.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.*;

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
