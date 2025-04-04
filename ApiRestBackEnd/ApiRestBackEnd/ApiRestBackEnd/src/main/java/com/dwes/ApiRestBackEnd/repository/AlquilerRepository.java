package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    public List<Alquiler> findAll();
    List<Alquiler> findByFechaInicioAfterAndFechaFinBefore(LocalDate fechaInicio, LocalDate fechaFin);
}
