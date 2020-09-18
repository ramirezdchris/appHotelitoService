package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer>{

}
