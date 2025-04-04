package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;
    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository){this.alquilerRepository=alquilerRepository;}
    @Transactional(readOnly = true)
    public List<Alquiler> obtenerAlquileres(){return alquilerRepository.findAll();}
}
