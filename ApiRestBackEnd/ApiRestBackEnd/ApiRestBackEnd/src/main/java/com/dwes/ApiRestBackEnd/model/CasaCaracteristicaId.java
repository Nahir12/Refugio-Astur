package com.dwes.ApiRestBackEnd.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor    // Constructor sin argumentos requerido
@AllArgsConstructor   // Constructor con todos los argumentos
@Embeddable
public class CasaCaracteristicaId implements Serializable {
        private Long casaId;
        private Long caracteristicaId;
}
