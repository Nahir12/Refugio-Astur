package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.AlquilerDTO;
import com.dwes.ApiRestBackEnd.model.Alquiler;
import com.dwes.ApiRestBackEnd.service.AlquilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlquilerController {
    private final AlquilerService alquilerService;

    @Autowired
    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping("/crearAlquiler")
    public Alquiler crearAlquiler(@RequestBody AlquilerDTO alquiler) {
        return alquilerService.crearAlquiler(alquiler);
    }

    @GetMapping("/ObtenerAlquileres")
    public List<Alquiler> obtenerAlquileres() {
        return alquilerService.obtenerAlquileres();
    }

    // Endpoint para eliminar un alquiler según su idAlquiler.
    // Si el alquiler no existe, se retornará un error 404 con el mensaje "NO existe ese alquiler".
    @DeleteMapping("/eliminarAlquiler/{id}")
    public ResponseEntity<Void> eliminarAlquiler(@PathVariable("id") Long idAlquiler) {
        alquilerService.eliminarAlquiler(idAlquiler);
        return ResponseEntity.noContent().build();
    }
}
