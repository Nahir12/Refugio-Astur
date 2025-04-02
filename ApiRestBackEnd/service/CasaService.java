package com.dwes.ApiRestBackEnd.service;

import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasaService {
    private final CasaRepository casaRepository;
    @Autowired
    public CasaService(CasaRepository casaRepository){this.casaRepository=casaRepository;}
    @Transactional(readOnly = true)
    public List<Casa> obtenerCasas(){
        return casaRepository.findAll();
    }
}
