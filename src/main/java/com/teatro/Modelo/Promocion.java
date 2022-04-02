package com.teatro.Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public abstract class Promocion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Column(unique = true)
	private String titulo;
	
	private String urlImagen;
	
	private boolean esActiva = true;
	
	@ManyToMany
	private ArrayList<Show> shows;
	
	@NotNull
	private float beneficio;
	
	
	
	public boolean esNulo(){
		
		return false;
	}
	
	public int getDuracionMinShow() {
		
		return shows.get(0).getDuracionMinShow();
	}
	
	public LocalDateTime getFechaShow() {
		
		return shows.get(0).getFechaShow();
	}
	
	
	
	
	
	
}
