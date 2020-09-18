package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Habitacion;

@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion, Integer>{

}
