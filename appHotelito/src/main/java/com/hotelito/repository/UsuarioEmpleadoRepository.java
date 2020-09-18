package com.hotelito.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.hotelito.model.UsuarioEmpleado;

@Repository
public interface UsuarioEmpleadoRepository extends CrudRepository<UsuarioEmpleado, Integer>{

}
