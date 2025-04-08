package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<Casa> buscarCasas(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) Double precio,
            @RequestParam(required = false) String caracteristica,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        LocalDate inicio = (fechaInicio != null) ? fechaInicio : LocalDate.now();
        LocalDate fin = (fechaFin != null) ? fechaFin : null;

        return casaService.buscarCasasFiltro(ciudad, precio, caracteristica, inicio, fin);
    }

}
