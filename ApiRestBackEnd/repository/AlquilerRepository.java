package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Alquiler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlquilerRepository extends CrudRepository<Alquiler, Long> {
    public List<Alquiler> findAll();
}
