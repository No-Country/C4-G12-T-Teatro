package com.teatro.dto.tiket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiketDto{
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaCompra;
	
    private float precio;
    private String nombreApellido;
    private int cantidadEntradas;
    private String showTitulo;
    private float showPrecio;
    
    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
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
