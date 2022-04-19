package com.teatro.modelo;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala implements Serializable{

	private static final long serialVersionUID = 3277164960109294419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60)
	@Column(unique = true)
	private String nombre;

	@Min(100)
	@Max(5000)
	@NotNull
	private int capacidad;

	@Min(10)
	@Max(100)
	@NotNull
	private int filas;
	
	@OneToMany(mappedBy = "sala")
	private List<Show> shows;
}
