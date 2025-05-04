package com.dwes.ApiRestBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor    // Constructor sin argumentos requerido
@AllArgsConstructor   // Constructor con todos los argumentos
public class CasaCaracteristicasDTO {
    private long idCasa;
    private long idCaracteristica;
}
