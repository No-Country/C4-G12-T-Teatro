package com.teatro.dto.butaca;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapFilaButacaDto {

	private Map<Integer,Integer[]> butacas;
	
	private int cantidad;
	
}
