package com.dwes.ApiRestBackEnd.repository;

import com.dwes.ApiRestBackEnd.model.Casa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaRepository extends CrudRepository<Casa, Long> {
     List<Casa> findAll();
}
