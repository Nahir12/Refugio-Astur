package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.Caracteristica;
import com.dwes.ApiRestBackEnd.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/caracteristica")
public class CaracteristicaController {
    private final CaracteristicaService caracteristicaService;
    @Autowired
    public CaracteristicaController(CaracteristicaService caracteristicaService){this.caracteristicaService=caracteristicaService;}
    @GetMapping("/obtenerCaracteristica")
    public List<Caracteristica> obtenerCaracteristicas(){return caracteristicaService.obtenerCaracteristicas();}
}
