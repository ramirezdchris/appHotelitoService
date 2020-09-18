package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.MenuDia;

@Repository
public interface MenuDiaRepository extends CrudRepository<MenuDia, Integer>{

}
