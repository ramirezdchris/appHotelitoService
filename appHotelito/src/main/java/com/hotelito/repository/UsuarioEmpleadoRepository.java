package com.hotelito.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelito.model.UsuarioEmpleado;


@Repository
public interface UsuarioEmpleadoRepository extends CrudRepository<UsuarioEmpleado, Integer>{
	
	@Query(value = "SELECT UE FROM UsuarioEmpleado UE WHERE usuario = :usuario AND clave = :clave")
	public List<UsuarioEmpleado> loginEmpleado(@Param("usuario") String usuario, @Param("clave") String clave);
	
	@Query(value = "SELECT UE FROM UsuarioEmpleado UE WHERE usuario = :usuario AND clave = :clave")
	public UsuarioEmpleado usarioVal(@Param("usuario")String usn, @Param("clave")String clave);

}
