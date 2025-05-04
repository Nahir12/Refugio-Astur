package com.dwes.ApiRestBackEnd.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor    // Constructor por defecto necesario
@AllArgsConstructor   // Constructor completo opcional
@Entity
@Table(name = "alquileres")
public class Alquiler {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idAlquiler;

        @ManyToOne
        @JoinColumn(name = "usuarioId", nullable = false)
        private Usuario usuario;

        @ManyToOne
        @JoinColumn(name = "casaId", nullable = false)
        private Casa casa;

        @Column(name = "fecha_inicio", nullable = false)
        private LocalDate fechaInicio;

        @Column(name = "fecha_fin", nullable = false)
        private LocalDate fechaFin;
}
