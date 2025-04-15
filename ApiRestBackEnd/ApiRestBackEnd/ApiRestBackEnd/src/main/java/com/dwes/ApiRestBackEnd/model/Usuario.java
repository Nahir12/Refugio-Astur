package com.dwes.ApiRestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nombre;
    private String email;
    private String contraseña;
    private String tipo_usuario;
    private boolean pago;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Alquiler>alquilers;
}
