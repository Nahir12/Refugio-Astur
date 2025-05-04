package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.CasaCaracteristicasDTO;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.model.Caracteristica;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicaId;
import com.dwes.ApiRestBackEnd.repository.CasaCaracteristicasRepository;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import com.dwes.ApiRestBackEnd.repository.CaracteristicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasaCaracteristicaService {

    private final CasaCaracteristicasRepository casaCaracteristicasRepository;
    private final CasaRepository casaRepository;
    private final CaracteristicaRepository caracteristicaRepository;

    public CasaCaracteristicaService(CasaCaracteristicasRepository casaCaracteristicasRepository,
                                     CasaRepository casaRepository,
                                     CaracteristicaRepository caracteristicaRepository) {
        this.casaCaracteristicasRepository = casaCaracteristicasRepository;
        this.casaRepository = casaRepository;
        this.caracteristicaRepository = caracteristicaRepository;
    }

    // Mapea el DTO a la entidad, recuperando las asociaciones existentes
    public CasaCaracteristicas mapToEntity(CasaCaracteristicasDTO dto) {
        Casa casa = casaRepository.findById(dto.getIdCasa())
                .orElseThrow(() -> new RuntimeException("Casa no encontrada con id: " + dto.getIdCasa()));
        Caracteristica caracteristica = caracteristicaRepository.findById(dto.getIdCaracteristica())
                .orElseThrow(() -> new RuntimeException("Característica no encontrada con id: " + dto.getIdCaracteristica()));

        // Crear la clave compuesta usando los valores del DTO
        CasaCaracteristicaId id = new CasaCaracteristicaId(dto.getIdCasa(), dto.getIdCaracteristica());

        return CasaCaracteristicas.builder()
                .idCasaCaracteristica(id)
                .casa(casa)
                .caracteristica(caracteristica)
                .build();
    }

    // Mapea la entidad a DTO para la respuesta
    public CasaCaracteristicasDTO mapToDTO(CasaCaracteristicas entity) {
        return CasaCaracteristicasDTO.builder()
                .idCasa(entity.getCasa().getIdCasa())
                .idCaracteristica(entity.getCaracteristica().getIdCaracteristica())
                .build();
    }

    // Crea una nueva CasaCaracteristicas a partir del DTO y devuelve el DTO resultante
    @Transactional
    public CasaCaracteristicasDTO crearCasaCaracteristica(CasaCaracteristicasDTO dto) {
        CasaCaracteristicas entity = mapToEntity(dto);
        CasaCaracteristicas saved = casaCaracteristicasRepository.save(entity);
        return mapToDTO(saved);
    }

    // Obtiene la lista de CasaCaracteristicas y la convierte a DTO
    @Transactional(readOnly = true)
    public List<CasaCaracteristicasDTO> obtenerCaracteristicas() {
        return casaCaracteristicasRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método para eliminar una CasaCaracteristicas a partir del DTO.
    // Lanza un error si la relación no existe.
    @Transactional
    public void eliminarCasaCaracteristica(CasaCaracteristicasDTO dto) {
        CasaCaracteristicaId id = new CasaCaracteristicaId(dto.getIdCasa(), dto.getIdCaracteristica());
        if (!casaCaracteristicasRepository.existsById(id)) {
            throw new RuntimeException("La relación CasaCaracteristicas no existe con idCasa: " +
                    dto.getIdCasa() + " y idCaracteristica: " + dto.getIdCaracteristica());
        }
        casaCaracteristicasRepository.deleteById(id);
    }
}
