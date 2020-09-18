package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Promocion;

@Repository
public interface PromocionRepository extends CrudRepository<Promocion, Integer>{

}
