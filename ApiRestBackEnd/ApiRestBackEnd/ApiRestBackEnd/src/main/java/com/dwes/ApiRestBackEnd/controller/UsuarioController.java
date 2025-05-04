package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.UsuarioDTO;
import com.dwes.ApiRestBackEnd.dto.UsuarioUpdateDTO;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint para crear un usuario.
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    // Endpoint para obtener todos los usuarios.
    @GetMapping("/obtener")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerListadoUsuarios();
    }

    // Endpoint para eliminar un usuario.
    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Usuario eliminado con éxito.");
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Usuario> modificarUsuario(
            @PathVariable("id") Long id,
            @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario actualizado = usuarioService.modificarDatosUsuario(id, usuarioUpdateDTO);
        return ResponseEntity.ok(actualizado);
    }


}
