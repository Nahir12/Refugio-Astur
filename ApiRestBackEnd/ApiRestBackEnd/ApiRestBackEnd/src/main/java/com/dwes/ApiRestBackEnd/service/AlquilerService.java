package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.AlquilerDTO;
import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.model.Usuario;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;
    private final CasaRepository casaRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository, CasaRepository casaRepository) {
        this.alquilerRepository = alquilerRepository;
        this.casaRepository = casaRepository;
    }

    public AlquilerDTO mapToRequestDTO(Alquiler alquiler){
        return AlquilerDTO.builder()
                .casaID(alquiler.getCasa().getIdCasa())
                .usuarioID(alquiler.getUsuario().getIdUsuario())
                .fechaInicio(alquiler.getFechaInicio())
                .fechaFin(alquiler.getFechaFin())
                .build();
    }

    @Transactional
    public Alquiler crearAlquiler(AlquilerDTO alquilerDTO) {
        // Se recupera la entidad Casa a partir del ID proporcionado.
        Casa casa = casaRepository.findById(alquilerDTO.getCasaID())
                .orElseThrow(() -> new RuntimeException("Casa no encontrada"));

        // Se omite la validación de usuario. Si se proporciona un ID, se crea una referencia;
        // de lo contrario se deja el campo en null.
        Usuario usuario = null;
        if (alquilerDTO.getUsuarioID() != null) {
            usuario = Usuario.builder()
                    .idUsuario(alquilerDTO.getUsuarioID())
                    .build();
        }

        // Se construye el objeto Alquiler a partir del DTO y de la entidad Casa.
        Alquiler alquiler = Alquiler.builder()
                .usuario(usuario)
                .casa(casa)
                .fechaInicio(alquilerDTO.getFechaInicio())
                .fechaFin(alquilerDTO.getFechaFin())
                .build();

        // Se guarda el alquiler en la base de datos.
        return alquilerRepository.save(alquiler);
    }

    @Transactional(readOnly = true)
    public List<Alquiler> obtenerAlquileres() {
        return alquilerRepository.findAll();
    }

    // Método delete: elimina un alquiler según su idAlquiler.
    // Lanza un error con el mensaje "NO existe ese alquiler" si no se encuentra.
    @Transactional
    public void eliminarAlquiler(Long idAlquiler) {
        if (!alquilerRepository.existsById(idAlquiler)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO existe ese alquiler");
        }
        alquilerRepository.deleteById(idAlquiler);
    }
}

