package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import com.dwes.ApiRestBackEnd.repository.CasaCaracteristicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasaCaracteristicaService {
    private final CasaCaracteristicasRepository casaCaracteristicasRepository;
    @Autowired
    public CasaCaracteristicaService(CasaCaracteristicasRepository casaCaracteristicasRepository){this.casaCaracteristicasRepository=casaCaracteristicasRepository;}
    @Transactional(readOnly = true)
    public List<CasaCaracteristicas> obtenerCaracteristicas(){return casaCaracteristicasRepository.findAll();}
}
