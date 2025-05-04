package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.UsuarioDTO;
import com.dwes.ApiRestBackEnd.dto.UsuarioUpdateDTO;
import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.repository.UsuarioRepository;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final AlquilerRepository alquilerRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, AlquilerRepository alquilerRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alquilerRepository = alquilerRepository;
    }

    public UsuarioDTO mapToRequestDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nombre(usuario.getNombre())
                .contraseña(usuario.getContraseña())
                .email(usuario.getEmail())
                .build();
    }

    // CREAR USUARIO
    @Transactional
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }

        // Se considera usuario temporal si no se provee contraseña.
        boolean esTemporal = usuarioDTO.getContraseña() == null;

        Usuario usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getEmail())
                .contraseña(esTemporal ? null : usuarioDTO.getContraseña())
                .tipo_usuario("normal")
                .esTemporal(esTemporal)
                .build();

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> obtenerListadoUsuarios() {
        return usuarioRepository.findAll();
    }

    // ELIMINAR USUARIO
    @Transactional
    public void eliminarUsuario(Long idUsuario) {
        // Buscamos el usuario; si no existe, lanzamos excepción.
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id: " + idUsuario));

        // Eliminar registros asociados (por ejemplo, de alquileres).
        if (usuario.getAlquilers() != null && !usuario.getAlquilers().isEmpty()) {
            for (Object a : usuario.getAlquilers()) {
                alquilerRepository.delete((Alquiler) a);
            }
        }
        usuarioRepository.delete(usuario);
    }
    @Transactional
    public Usuario modificarDatosUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        // Buscamos el usuario por id; de no existir, lanzamos excepción.
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con id: " + id));

        // Solo permitimos modificar usuarios registrados.
        if (usuario.getEsTemporal() != null && usuario.getEsTemporal()) {
            throw new IllegalArgumentException("No se pueden modificar datos de un usuario temporal");
        }

        // Actualizar nombre si se envía
        if (usuarioUpdateDTO.getNombre() != null && !usuarioUpdateDTO.getNombre().isBlank()) {
            usuario.setNombre(usuarioUpdateDTO.getNombre());
        }

        // Actualizar email si se envía
        if (usuarioUpdateDTO.getEmail() != null && !usuarioUpdateDTO.getEmail().isBlank()) {
            // Verificar si se cambió el email y que además no esté duplicado.
            if (!usuario.getEmail().equalsIgnoreCase(usuarioUpdateDTO.getEmail()) &&
                    usuarioRepository.existsByEmail(usuarioUpdateDTO.getEmail())) {
                throw new IllegalArgumentException("El email proporcionado ya está en uso");
            }
            usuario.setEmail(usuarioUpdateDTO.getEmail());
        }

        // Actualizar contraseña si se envía; en caso contrario, conserva la actual.
        if (usuarioUpdateDTO.getContraseña() != null && !usuarioUpdateDTO.getContraseña().isBlank()) {
            usuario.setContraseña(usuarioUpdateDTO.getContraseña());
            // No se afecta esTemporal; se mantiene que es un usuario registrado.
        }

        return usuarioRepository.save(usuario);
    }


}
