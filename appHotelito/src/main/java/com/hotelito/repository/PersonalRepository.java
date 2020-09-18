package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Personal;

@Repository
public interface PersonalRepository extends CrudRepository<Personal, Integer>{

}
