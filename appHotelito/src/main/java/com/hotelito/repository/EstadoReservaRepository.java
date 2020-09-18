package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.EstadoReserva;

@Repository
public interface EstadoReservaRepository extends CrudRepository<EstadoReserva, Integer>{

}
