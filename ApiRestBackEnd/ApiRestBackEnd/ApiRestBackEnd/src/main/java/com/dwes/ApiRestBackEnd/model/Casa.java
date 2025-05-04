package com.dwes.ApiRestBackEnd.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Data
@Builder
@NoArgsConstructor    // Constructor por defecto necesario
@AllArgsConstructor   // Constructor completo opcional
@Entity
@Table(name = "casas")  // Asegúrate que el nombre de la tabla coincida con tu base de datos
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCasa;

    private String nombre;
    private String direccion;
    private String ciudad;
    private Double precio;
    private String descripcion;
    private int numHabitaciones;

    @Column(name = "numBaños")
    private int numBaños;

    // Si la entidad tiene otra colección, por ejemplo, casaCaracteristicas, inclúyela aquí
    // private List<CasaCaracteristica> casaCaracteristicas;

    @OneToMany(mappedBy = "casa")
    @JsonIgnore // Evita la serialización recursiva (o reemplázalo por @JsonManagedReference/@JsonBackReference si deseas controlarlo)
    private List<Alquiler> alquilers;
}
