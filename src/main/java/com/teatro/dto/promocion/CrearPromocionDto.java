package com.teatro.dto.promocion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearPromocionDto {

	@NotBlank
	@Size(max = 60)
	private String titulo;
	
	@NotNull
	@Min(1)
	private Long showId;
}
