package com.teatro.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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

	private boolean ocupada = false;

	@ManyToOne
	@JoinColumn(name = "show_id")
	@NotNull
	private Show show;

	@ManyToMany(mappedBy = "butacas")
	private List<Tiket> tiket;

	@ManyToOne
	@JoinColumn(name = "seccion_id")
	private Seccion seccion;
}

