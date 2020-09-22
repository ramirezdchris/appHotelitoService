package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Integer idPersonal;
    @Column(name = "nombres")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
    @ManyToOne
    @JoinColumn(name = "rol")
    private Rol rol;
    
	public Personal() {
		super();
	}

	public Personal(Integer idPersonal, String nombre, String apellido, Rol rol) {
		super();
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}

	public Integer getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(Integer idPersonal) {
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Personal [idPersonal=" + idPersonal + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol
				+ "]";
	}
	
}
