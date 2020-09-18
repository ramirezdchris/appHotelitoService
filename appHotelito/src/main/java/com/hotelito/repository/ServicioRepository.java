package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotelito.model.Servicio;
import com.hotelito.model.TipoHabitacion;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer>{

}
