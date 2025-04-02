package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "alquileres")
public class Alquiler {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idAlquiler;

        @ManyToOne
        @JsonBackReference
        @JoinColumn(name = "usuarioId", nullable = false)
        private Usuario usuario;

        @ManyToOne
        @JsonBackReference
        @JoinColumn(name = "casaId", nullable = false)
        private Casa casa;

        @Column(name = "fecha_inicio", nullable = false)
        private java.time.LocalDate fechaInicio;

        @Column(name = "fecha_fin", nullable = false)
        private java.time.LocalDate fechaFin;
}
