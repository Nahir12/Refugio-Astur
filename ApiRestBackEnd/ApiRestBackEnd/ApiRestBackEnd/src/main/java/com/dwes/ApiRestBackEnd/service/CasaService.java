package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import com.dwes.ApiRestBackEnd.repository.CasaCaracteristicasRepository;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CasaService {
    private final CasaRepository casaRepository;
    private final AlquilerRepository alquilerRepository;
    private final CasaCaracteristicasRepository casaCaracteristicasRepository;
    @Autowired
    public CasaService(CasaRepository casaRepository,AlquilerRepository alquilerRepository,CasaCaracteristicasRepository casaCaracteristicasRepository){
        this.casaRepository=casaRepository;
        this.alquilerRepository=alquilerRepository;
        this.casaCaracteristicasRepository=casaCaracteristicasRepository;
    }
    //sacar todas las casas, sea como sea
    @Transactional(readOnly = true)
    public List<Casa> obtenerCasas(){
        return casaRepository.findAll();
    }

    public List<Casa> buscarCasasFiltro(String ciudad, Double precio, String caracteristica, LocalDate fechaInicio, LocalDate fechaFin) {
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
            casas = casas.stream()
                    .filter(casa -> casa.getPrecio() <= precio)
                    .collect(Collectors.toList());
        }

        // Filtrar por características (solo si se proporcionan)
        if (caracteristica != null && !caracteristica.isEmpty()) {
            String[] caracteristicasArray = caracteristica.split(","); // Suponiendo que las características se pasan separadas por comas.
            List<Long> idsCasasConCaracteristicas = Arrays.stream(caracteristicasArray)
                    .flatMap(caract -> casaCaracteristicasRepository.findByCaracteristicaNombre(caract).stream())
                    .map(casaCaracteristicas -> casaCaracteristicas.getCasa().getIdCasa())
                    .distinct()
                    .collect(Collectors.toList());

            // Filtrar las casas que cumplan TODAS las características especificadas
            casas = casas.stream()
                    .filter(casa -> idsCasasConCaracteristicas.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());
        }

        // Filtrar por fechas (solo si se proporcionan una o ambas fechas)
        if (fechaInicio != null || fechaFin != null) {
            List<Long> idsCasasOcupadas = alquilerRepository.findAll().stream()
                    .filter(alquiler -> {
                        boolean condicionInicio = (fechaInicio == null || alquiler.getFechaFin().isAfter(fechaInicio));
                        boolean condicionFin = (fechaFin == null || alquiler.getFechaInicio().isBefore(fechaFin));
                        return condicionInicio && condicionFin;
                    })
                    .map(alquiler -> alquiler.getCasa().getIdCasa())
                    .collect(Collectors.toList());

            // Excluir las casas ocupadas
            casas = casas.stream()
                    .filter(casa -> !idsCasasOcupadas.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());
        }
        

        return casas;
    }


}
