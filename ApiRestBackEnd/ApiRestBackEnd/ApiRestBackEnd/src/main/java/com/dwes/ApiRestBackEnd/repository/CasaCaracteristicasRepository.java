package com.dwes.ApiRestBackEnd.repository;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicaId;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaCaracteristicasRepository extends JpaRepository<CasaCaracteristicas, CasaCaracteristicaId> {
    public List<CasaCaracteristicas> findAll();
    List<CasaCaracteristicas> findByCaracteristicaNombre(String nombreCaracteristica);
}
