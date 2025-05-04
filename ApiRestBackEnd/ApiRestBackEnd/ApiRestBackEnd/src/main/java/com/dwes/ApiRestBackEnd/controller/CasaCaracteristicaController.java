package com.dwes.ApiRestBackEnd.controller;

import com.dwes.ApiRestBackEnd.dto.CasaCaracteristicasDTO;
import com.dwes.ApiRestBackEnd.service.CasaCaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/casaCaracteristica")
public class CasaCaracteristicaController {

    private final CasaCaracteristicaService casaCaracteristicaService;

    @Autowired
    public CasaCaracteristicaController(CasaCaracteristicaService casaCaracteristicaService) {
        this.casaCaracteristicaService = casaCaracteristicaService;
    }

    // Endpoint para crear una CasaCaracteristicas a partir del DTO
    @PostMapping("/crearCasaCaracteristica")
    public CasaCaracteristicasDTO crearCasaCaracteristica(@RequestBody CasaCaracteristicasDTO dto) {
        return casaCaracteristicaService.crearCasaCaracteristica(dto);
    }

    // Endpoint para obtener la lista de CasaCaracteristicas como DTOs
    @GetMapping("/obtenerCaracteristicas")
    public List<CasaCaracteristicasDTO> obtenerCaracteristicas() {
        return casaCaracteristicaService.obtenerCaracteristicas();
    }

    // Endpoint para eliminar una CasaCaracteristicas a partir del DTO.
    // Retorna 204 No Content si se elimina correctamente.
    @DeleteMapping("/eliminarCasaCaracteristica")
    public ResponseEntity<Void> eliminarCasaCaracteristica(@RequestBody CasaCaracteristicasDTO dto) {
        casaCaracteristicaService.eliminarCasaCaracteristica(dto);
        return ResponseEntity.noContent().build();
    }
}
