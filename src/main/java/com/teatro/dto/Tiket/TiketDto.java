package com.teatro.dto.Tiket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiketDto implements Serializable {
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
    public static class ButacaDto implements Serializable {
        private Long id;
        private boolean ocupada = false;
    }
}
