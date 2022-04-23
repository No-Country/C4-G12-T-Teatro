package com.teatro.dto.promocion;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditarPromocionDto {

	@NotBlank
	@Size(max = 60)
	private String titulo;
	
	@Min(0)
	@Max(100)
	private float descuento;
}
