package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelito.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

}
