package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "casas")
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCasa;
    private String nombre;
    private String direccion;
    private String ciudad;
    private double precio;
    private String  descripcion;
    @JsonIgnore
    @OneToMany(mappedBy = "casa")
    private List<CasaCaracteristicas> casaCaracteristicas;

    @OneToMany(mappedBy = "casa")
    @JsonManagedReference
    private List<Alquiler> alquilers;

}
