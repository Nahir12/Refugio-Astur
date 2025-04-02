package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Caracteristica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaRepository extends CrudRepository<Caracteristica,Long> {
    List<Caracteristica> findAll();
}
