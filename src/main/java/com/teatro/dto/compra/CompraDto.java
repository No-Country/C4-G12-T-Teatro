package com.teatro.dto.compra;

import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompraDto {
	
	@Size(max = 50)
	private String nombre;
	
	@Size(max = 50)
	private String apellido;
	
	@Email
	private String email;
	
	@Min(18)
	@Max(120)
	private int edad;
	
	@NotNull
	private Map<Integer,Integer[]> butacas;
	
	@NotNull
	private int cantidad;
	
}
