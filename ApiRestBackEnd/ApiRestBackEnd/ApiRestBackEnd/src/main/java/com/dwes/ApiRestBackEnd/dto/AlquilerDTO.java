package com.dwes.ApiRestBackEnd.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AlquilerDTO {
    private Long usuarioID;
    private Long casaID;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
