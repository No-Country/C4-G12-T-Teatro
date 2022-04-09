package com.teatro.entity;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@Column(name = "DESCRIPCION")
	private String nombre;
	@Column(name = "ESTADO")
	private boolean estado;

	@OneToMany(mappedBy = "categoria")
	private Set<Show> show;
}
