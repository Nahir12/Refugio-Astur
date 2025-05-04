package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.CaracteristicaDTO;
import com.dwes.ApiRestBackEnd.model.Caracteristica;
import com.dwes.ApiRestBackEnd.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    private final CaracteristicaService caracteristicaService;

    @Autowired
    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @PostMapping("/crearCaracteristica")
    public Caracteristica crearCaracteristica(@RequestBody CaracteristicaDTO caracteristica) {
        return caracteristicaService.crearCaracteristica(caracteristica);
    }

    @GetMapping("/obtenerCaracteristica")
    public List<CaracteristicaDTO> obtenerCaracteristicas() {
        return caracteristicaService.obtenerCaracteristicas();
    }

    // Nuevo endpoint para eliminar una característica
    @DeleteMapping("/eliminarCaracteristica/{id}")
    public void eliminarCaracteristica(@PathVariable("id") Long idCaracteristica) {
        caracteristicaService.eliminarCaracteristica(idCaracteristica);
    }
}
