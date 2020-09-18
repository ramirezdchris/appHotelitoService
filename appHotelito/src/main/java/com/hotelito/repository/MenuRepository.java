package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer>{

}
