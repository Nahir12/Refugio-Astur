package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.model.Casa;
import com.dwes.ApiRestBackEnd.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/casa")
public class CasaController {
    private final CasaService casaService;
    @Autowired
    public CasaController(CasaService casaService){this.casaService=casaService;}
    @GetMapping("/obtenerCasas")
    public List<Casa> obtenerCasas(){return casaService.obtenerCasas();}
}
