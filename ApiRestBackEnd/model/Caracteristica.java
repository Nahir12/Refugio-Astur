package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCaracteristica;
    private String nombre;
    private String descripcion;
    @JsonIgnore
    @OneToMany(mappedBy = "caracteristica")
    private List<CasaCaracteristicas>casaCaracteristicas;
}
