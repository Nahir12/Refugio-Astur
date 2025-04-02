package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import com.dwes.ApiRestBackEnd.service.CasaCaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/casaCaracteristica")
public class CasaCaracteristicaController {
    private final CasaCaracteristicaService casaCaracteristicaService;
    @Autowired
    public CasaCaracteristicaController(CasaCaracteristicaService casaCaracteristicaService){this.casaCaracteristicaService=casaCaracteristicaService;}
    @GetMapping("/obtenerCaracteristicas")
    public List<CasaCaracteristicas> obtenerCaracteristicas(){return casaCaracteristicaService.obtenerCaracteristicas();}
}
