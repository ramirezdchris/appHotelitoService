package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelito.model.Cliente;
import com.hotelito.model.TipoHabitacion;

@Repository
public interface TipoHabitacionRepository extends CrudRepository<TipoHabitacion, Integer>{

}
