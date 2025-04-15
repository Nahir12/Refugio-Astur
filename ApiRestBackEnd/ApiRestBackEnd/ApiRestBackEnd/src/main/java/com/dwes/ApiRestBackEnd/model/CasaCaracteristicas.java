package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "casa_caracteristicas")
public class CasaCaracteristicas {
    @EmbeddedId
    private CasaCaracteristicaId idCasaCaracteristica;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "casaId", insertable = false, updatable = false) // Evita la duplicación del mapeo
    private Casa casa;

    @ManyToOne
    @JoinColumn(name = "caracteristicaId", insertable = false, updatable = false) // Evita la duplicación del mapeo
    private Caracteristica caracteristica;

}
