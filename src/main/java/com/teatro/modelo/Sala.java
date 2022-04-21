package com.teatro.modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@ToStringExclude

	@JsonBackReference
	@OneToMany(mappedBy = "sala")
	private List<Show> shows = new ArrayList<>();
	
	public boolean esNula() {
		return false;
	}
	
	public Map<Integer, Butaca []> getMapaSala(Show show){
		List<Butaca> butacas = show.getButacas();
		Map<Integer, Butaca[]> mapa = new HashMap<>();
		
		int butacaPorFila = capacidad / filas;
		int butacasSobrantes = capacidad % filas;
		
		for (int i = 0; i < filas; i++) {
			if (butacasSobrantes > 0) {
				mapa.put(i + 1 , new Butaca[butacaPorFila + 1]);
				butacasSobrantes--;
			} else {
				mapa.put(i + 1, new Butaca[butacaPorFila]); 
			}
		}
		for (Butaca butaca : butacas) {
			Butaca [] but = mapa.get(butaca.getFila());
			but[butaca.getNumero()-1] = butaca;
			mapa.replace(butaca.getFila(), but);
		}
		
		return mapa;
	}
	
	public boolean esButacaExistenteEnSala(Butaca butaca) {
		Map<Integer, Integer[]> mapa = new HashMap<>();
		
		int butacaPorFila = capacidad / filas;
		int butacasSobrantes = capacidad % filas;
		
		for (int i = 0; i < filas; i++) {
			if (butacasSobrantes > 0) {
				mapa.put(i + 1 , new Integer[butacaPorFila + 1]);
				butacasSobrantes--;
			} else {
				mapa.put(i + 1, new Integer[butacaPorFila]); 
			}
		}
		
		return mapa.get(butaca.getFila()).length < butaca.getNumero();
	}
}
