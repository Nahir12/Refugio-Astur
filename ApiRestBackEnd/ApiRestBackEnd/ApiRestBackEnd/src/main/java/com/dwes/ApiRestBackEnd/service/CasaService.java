package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.dto.CasaRequestDTO;
import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import com.dwes.ApiRestBackEnd.repository.CasaCaracteristicasRepository;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasaService {
    private final CasaRepository casaRepository;
    private final AlquilerRepository alquilerRepository;
    private final CasaCaracteristicasRepository casaCaracteristicasRepository;

    @Autowired
    public CasaService(CasaRepository casaRepository, AlquilerRepository alquilerRepository,
                       CasaCaracteristicasRepository casaCaracteristicasRepository) {
        this.casaRepository = casaRepository;
        this.alquilerRepository = alquilerRepository;
        this.casaCaracteristicasRepository = casaCaracteristicasRepository;
    }

    public CasaRequestDTO mapToRequestDTO(Casa casa) {
        return CasaRequestDTO.builder()
                .ciudad(casa.getCiudad())
                .descripcion(casa.getDescripcion())
                .nombre(casa.getNombre())
                .direccion(casa.getDireccion())
                .numHabitaciones(casa.getNumHabitaciones())
                .numBaños(casa.getNumBaños())
                .precio(casa.getPrecio())
                .build();
    }

    public Casa mapToEntity(CasaRequestDTO casaDTO) {
        return Casa.builder()
                .nombre(casaDTO.getNombre())
                .direccion(casaDTO.getDireccion())
                .ciudad(casaDTO.getCiudad())
                .precio(casaDTO.getPrecio())
                .descripcion(casaDTO.getDescripcion())
                .numHabitaciones(casaDTO.getNumHabitaciones())
                .numBaños(casaDTO.getNumBaños())
                // Si la entidad tiene más campos, se pueden mapear aquí.
                .build();
    }

    @Transactional
    public Casa crearCasa(CasaRequestDTO casaDTO) {
        Casa casa = mapToEntity(casaDTO);
        return casaRepository.save(casa);
    }

    // Obtiene todas las casas con los datos necesarios
    @Transactional(readOnly = true)
    public List<CasaRequestDTO> obtenerCasas() {
        return casaRepository.findAll()
                .stream()
                .map(this::mapToRequestDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Casa> buscarCasasFiltro(String ciudad, Double precio, String caracteristica, LocalDate fechaInicio, LocalDate fechaFin, Long numHabitaciones, Long numBaños) {
        // Obtener todas las casas de la base de datos inicialmente
        List<Casa> casas = casaRepository.findAll();

        // Filtrar por ciudad (solo si se proporciona)
        if (ciudad != null && !ciudad.isEmpty()) {
            casas = casas.stream()
                    .filter(casa -> casa.getCiudad().equalsIgnoreCase(ciudad))
                    .collect(Collectors.toList());
        }

        // Filtrar por precio (solo si se proporciona)
        if (precio != null) {
            casas = casas.stream().filter(casa -> casa.getPrecio() >= precio).collect(Collectors.toList());
        }
        if (numHabitaciones != null) {
            casas = casas.stream().filter(casa -> casa.getNumHabitaciones() >= numHabitaciones).collect(Collectors.toList());
        }
        if (numBaños != null) {
            casas = casas.stream().filter(casa -> casa.getNumBaños() >= numBaños).collect(Collectors.toList());
        }

        // Filtrar por características (solo si se proporcionan)
        if (caracteristica != null && !caracteristica.isEmpty()){
            String[] caracteristicasArray = caracteristica.split(",");
            List<Long> idsCasasConCaracteristicas = Arrays.stream(caracteristicasArray)
                    .flatMap(caract -> casaCaracteristicasRepository.findByCaracteristicaNombre(caract).stream())
                    .map(casaCaracteristicas -> casaCaracteristicas.getCasa().getIdCasa())
                    .distinct()
                    .collect(Collectors.toList());

            casas = casas.stream()
                    .filter(casa -> idsCasasConCaracteristicas.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());
        }

        if (fechaInicio != null && fechaFin != null) {
            List<Alquiler> alquileresDeLaCasa = new ArrayList<>();
            List<Long> idsCasas = new ArrayList<>();

            // Acumular todos los alquileres de las casas
            for (Casa casa : casas) {
                alquileresDeLaCasa.addAll(casa.getAlquilers());
            }

            // Filtrar alquileres según fechas
            for (Alquiler a : alquileresDeLaCasa) {
                if (a.getFechaInicio().equals(fechaFin) ||
                        a.getFechaFin().equals(fechaInicio)) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }

                if (!alquilerRepository.findAlquileresBetweenAndCasa(fechaInicio, a.getCasa().getIdCasa()).isEmpty()
                        || !alquilerRepository.findAlquileresBetweenAndCasa(fechaFin, a.getCasa().getIdCasa()).isEmpty()) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }
            }

            List<Long> listaIdsFinal = idsCasas.stream().distinct().collect(Collectors.toList());
            casas = casas.stream()
                    .filter(casa -> !listaIdsFinal.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());
        }

        return casas;
    }

    // Nuevo método para eliminar una casa
    @Transactional
    public void eliminarCasa(Long idCasa) {
        if (!casaRepository.existsById(idCasa)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe esa casa");
        }
        casaRepository.deleteById(idCasa);
    }
}
