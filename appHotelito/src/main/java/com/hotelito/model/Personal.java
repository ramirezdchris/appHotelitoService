package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Personal")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Personal implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
    private int idPersonal;
    @Column(name = "nombres")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
    
	public Personal() {
		super();
	}
	
	public Personal(int idPersonal, String nombre, String apellido) {
		super();
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public int getIdPersonal() {
		return idPersonal;
	}
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

    
    
    
}
