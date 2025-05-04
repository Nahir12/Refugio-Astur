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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    // Se permite nulo para los usuarios temporales.
    @Column(nullable = true)
    private String contraseña;

    @Column(nullable = false)
    private String tipo_usuario;

    // Usamos Boolean para permitir null si se requiere algún estado especial.
    private Boolean esTemporal;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore // Evita la serialización recursiva
    private List<Alquiler> alquilers;

}
