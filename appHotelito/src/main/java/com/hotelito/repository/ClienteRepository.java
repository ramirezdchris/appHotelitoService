package com.hotelito.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelito.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	
	@Query(value = "SELECT C FROM Cliente C WHERE usuario = :usuario AND clave = :clave")
	public Cliente loginCliente(@Param("usuario") String usuario, @Param("clave") String clave);

}
