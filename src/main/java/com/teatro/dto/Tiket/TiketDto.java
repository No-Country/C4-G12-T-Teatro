package com.teatro.dto.Tiket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiketDto{
	
    private LocalDate local;
    @NotNull
    @Min(1)
    @Max(100000)
    private float precio;
    @NotNull
    @Size(max = 100)
    private String nombreApellido;
    @NotNull
    @Min(1)
    private int cantidadEntradas;
    @NotBlank
    @Size(max = 200)
    private String showTitulo;
    @Min(0)
    @NotNull
    private float showPrecio;
    @NotNull
    private LocalDateTime showFechaShow;
    private List<ButacaDto> butacas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ButacaDto{
        private Long id;
        private int fila;
        private int numero;
    }
}
