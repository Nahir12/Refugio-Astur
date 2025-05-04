package com.dwes.ApiRestBackEnd.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioUpdateDTO {//Como el UsuarioDTO tiene los @NotBlanck no puedo usarlo para esto
    // El campo nombre es opcional en el update.
    private String nombre;

    // Si se envía, el email debe tener formato válido.
    @Email(message = "El email debe ser válido")
    private String email;

    // Si se envía, la contraseña debe tener al menos 6 caracteres.
    // Si no se envía, el usuario conservará su contraseña actual.
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contraseña;
}
