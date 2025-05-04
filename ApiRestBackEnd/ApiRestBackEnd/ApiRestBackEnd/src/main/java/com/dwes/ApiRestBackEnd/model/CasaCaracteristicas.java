package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor    // Constructor sin argumentos requerido por JPA
@AllArgsConstructor   // (Opcional) Constructor con todos los argumentos
@Entity
@Builder
@Table(name = "casa_caracteristicas")
public class CasaCaracteristicas {
    @EmbeddedId
    private CasaCaracteristicaId idCasaCaracteristica;

    @ManyToOne
    @JoinColumn(name = "casaId", insertable = false, updatable = false) // Evita la duplicación del mapeo
    private Casa casa;

    @ManyToOne
    @JoinColumn(name = "caracteristicaId", insertable = false, updatable = false) // Evita la duplicación del mapeo
    private Caracteristica caracteristica;

}
