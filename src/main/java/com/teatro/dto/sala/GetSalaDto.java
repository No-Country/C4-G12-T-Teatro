package com.teatro.dto.sala;

import java.util.List;

import com.teatro.modelo.Show;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSalaDto {

	private String nombre;
	
	private int capacidad;
	
	private int filas;
	
	private List<Show> shows;
}
