package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import com.dwes.ApiRestBackEnd.repository.CasaCaracteristicasRepository;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
        if (ciudad != null && !ciudad.isEmpty()) {//veo si no esta vacío
            casas = casas.stream()//convierto la lista en una secuencia para trabajar sobre ella
                    .filter(casa -> casa.getCiudad().equalsIgnoreCase(ciudad))//filtro las casas por la ciudad introducida
                    .collect(Collectors.toList());//vuelvo a hacerlo lista
        }

        // Filtrar por precio (solo si se proporciona)
        if (precio != null) {
            casas = casas.stream().filter(casa -> casa.getPrecio() >=precio).collect(Collectors.toList());
        }

        // Filtrar por características (solo si se proporcionan)
        if (caracteristica != null && !caracteristica.isEmpty()) {
            String[] caracteristicasArray = caracteristica.split(",");//cojo lo introducido y lo hago array
            List<Long> idsCasasConCaracteristicas = Arrays.stream(caracteristicasArray)
                    .flatMap(caract -> casaCaracteristicasRepository.findByCaracteristicaNombre(caract).stream())//: Para cada característica, busca las casas que tienen esa característica utilizando el repositorio
                    .map(casaCaracteristicas -> casaCaracteristicas.getCasa().getIdCasa())//Extrae el id de la casa asociada a cada características
                    .distinct()//me aseguro que no haya duplicados
                    .collect(Collectors.toList());

            // Filtrar las casas que cumplan TODAS las características especificadas anteriormente mediante la comparación de ids
           casas = casas.stream().filter(casa -> idsCasasConCaracteristicas.contains(casa.getIdCasa())).collect(Collectors.toList());
}

      /*  if(fechaInicio != null && fechaFin != null){
            List<Alquiler> alquileresDeLaCasa =new ArrayList<>();
            List<Long> idsCasas=new ArrayList<>();;
            for (Casa casa : casas) {
                alquileresDeLaCasa = casa.getAlquilers();
            }
            for(Alquiler a: alquileresDeLaCasa) {
                if (a.getFechaInicio().equals(fechaInicio) || a.getFechaFin().equals(fechaFin) || a.getFechaInicio().equals(fechaFin) || a.getFechaFin().equals(fechaInicio)) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }

                if (!alquilerRepository.findAlquileresBetweenAndCasa(fechaInicio, a.getCasa().getIdCasa()).isEmpty()) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }
                if (!alquilerRepository.findAlquileresBetweenAndCasa(fechaFin,a.getCasa().getIdCasa()).isEmpty()) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }

            }
           List<Long> listaIdsFinal= idsCasas.stream().distinct().collect(Collectors.toList());
            casas = casas.stream()
                    .filter(casa -> !listaIdsFinal.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());

            }*/
        if (fechaInicio != null && fechaFin != null) {
            List<Alquiler> alquileresDeLaCasa = new ArrayList<>();
            List<Long> idsCasas = new ArrayList<>();

            // Acumular todos los alquileres de las casas
            for (Casa casa : casas) {
                alquileresDeLaCasa.addAll(casa.getAlquilers());
            }

            // Filtrar alquileres según condiciones
            for (Alquiler a : alquileresDeLaCasa) {
                if (a.getFechaInicio().equals(fechaInicio) ||
                        a.getFechaFin().equals(fechaFin) ||
                        a.getFechaInicio().equals(fechaFin) ||
                        a.getFechaFin().equals(fechaInicio)) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }

                if (!alquilerRepository.findAlquileresBetweenAndCasa(fechaInicio, a.getCasa().getIdCasa()).isEmpty()) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }

                if (!alquilerRepository.findAlquileresBetweenAndCasa(fechaFin, a.getCasa().getIdCasa()).isEmpty()) {
                    idsCasas.add(a.getCasa().getIdCasa());
                }
            }

            // Eliminar duplicados de idsCasas
            List<Long> listaIdsFinal= idsCasas.stream().distinct().collect(Collectors.toList());
            // Filtrar las casas que deben excluirse según idsCasas
            casas = casas.stream()
                    .filter(casa -> !listaIdsFinal.contains(casa.getIdCasa()))
                    .collect(Collectors.toList());
        }

        return casas;
    }


}
