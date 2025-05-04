package com.dwes.ApiRestBackEnd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaracteristicaDTO {
    private String nombre;
    private String descripcion;
}
