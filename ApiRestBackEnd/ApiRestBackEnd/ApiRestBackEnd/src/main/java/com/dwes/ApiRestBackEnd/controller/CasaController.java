package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/casa")
public class CasaController {
    private final CasaService casaService;
    @Autowired
    public CasaController(CasaService casaService){this.casaService=casaService;}
    @GetMapping("/obtenerCasas")
    public List<Casa> obtenerCasas(){return casaService.obtenerCasas();}//obtener todas las casas
    //quizás desaparezca pronto

    @GetMapping("/buscarCasasFiltro")
    public List<Casa> buscarCasas(@RequestParam(required = false)String ciudad,
                                  @RequestParam(required = false) Double precio,
                                  @RequestParam(required = false) String caracteristica,
                                  @RequestParam(required = false) String fechaInicio,
                                  @RequestParam(required = false) String fechaFin) {
        LocalDate inicio = (fechaInicio != null) ? LocalDate.parse(fechaInicio) : LocalDate.now();
        LocalDate fin = (fechaFin != null) ? LocalDate.parse(fechaFin) : null;

        return casaService.buscarCasasFiltro(ciudad, precio, caracteristica, inicio, fin);
    }

}
