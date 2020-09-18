package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Integer>{

}
