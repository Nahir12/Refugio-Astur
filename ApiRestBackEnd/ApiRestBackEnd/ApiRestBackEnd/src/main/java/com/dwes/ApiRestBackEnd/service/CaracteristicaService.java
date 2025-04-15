package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Caracteristica;
import com.dwes.ApiRestBackEnd.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaracteristicaService {
    private final CaracteristicaRepository caracteristicaRepository;
    @Autowired
    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository){this.caracteristicaRepository=caracteristicaRepository;}
    @Transactional(readOnly = true)
    public List<Caracteristica> obtenerCaracteristicas(){
        return caracteristicaRepository.findAll();
    }


}
