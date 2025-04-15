package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    public List<Alquiler> findAll();
    List<Alquiler> findByFechaInicioAfterAndFechaFinBefore(LocalDate fechaInicio, LocalDate fechaFin);
    @Query("SELECT a FROM Alquiler a WHERE :fechaIntroducida BETWEEN a.fechaInicio AND a.fechaFin AND a.casa.idCasa = :idCasa")
    List<Alquiler> findAlquileresBetweenAndCasa(@Param("fechaIntroducida") LocalDate fechaIntroducida, @Param("idCasa") Long idCasa);

}
