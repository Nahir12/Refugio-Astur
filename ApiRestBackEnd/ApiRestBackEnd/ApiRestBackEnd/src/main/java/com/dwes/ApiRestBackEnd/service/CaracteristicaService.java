package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.CaracteristicaDTO;
import com.dwes.ApiRestBackEnd.model.Caracteristica;
import com.dwes.ApiRestBackEnd.repository.CaracteristicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaracteristicaService {

    private final CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }

    // Método de mapeo de entidad a DTO
    public CaracteristicaDTO mapToRequestDTO(Caracteristica caracteristica) {
        return CaracteristicaDTO.builder()
                .nombre(caracteristica.getNombre())
                .descripcion(caracteristica.getDescripcion())
                .build();
    }

    // Crear la característica a partir del DTO.
    @Transactional
    public Caracteristica crearCaracteristica(CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristica = Caracteristica.builder()
                .nombre(caracteristicaDTO.getNombre())
                .descripcion(caracteristicaDTO.getDescripcion())
                .build();
        return caracteristicaRepository.save(caracteristica);
    }

    @Transactional(readOnly = true)
    public List<CaracteristicaDTO> obtenerCaracteristicas() {
        return caracteristicaRepository.findAll()
                .stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    // Nuevo método para eliminar una característica
    @Transactional
    public void eliminarCaracteristica(Long idCaracteristica) {
        if (!caracteristicaRepository.existsById(idCaracteristica)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe esa característica");
        }
        caracteristicaRepository.deleteById(idCaracteristica);
    }
}
