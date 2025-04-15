package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlquilerController {
    private final AlquilerService alquilerService;
    @Autowired
    public AlquilerController(AlquilerService alquilerService){this.alquilerService=alquilerService;}
    @GetMapping("/ObtenerAlquileres")
    public List<Alquiler> obtenerAlquileres(){return alquilerService.obtenerAlquileres();}
}
