package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Rol;

@Repository
public interface RolRepository extends CrudRepository<Rol, Integer>{

}
