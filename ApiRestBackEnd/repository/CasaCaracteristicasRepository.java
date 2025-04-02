package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.CasaCaracteristicaId;
import com.dwes.ApiRestBackEnd.model.CasaCaracteristicas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaCaracteristicasRepository extends CrudRepository<CasaCaracteristicas, CasaCaracteristicaId> {
    public List<CasaCaracteristicas> findAll();

}
