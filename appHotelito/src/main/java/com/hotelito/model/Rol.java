package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Rol")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Rol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int id_rol;
    @Column(name = "nombre_rol")
    private String nombre_rol;

    public Rol() {
    }

    public Rol(int id_rol, String nombre_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
    }

    
    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
    
     @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id_rol:'");
        builder.append(id_rol);
        builder.append("',nombre_rol:'");
        builder.append(nombre_rol);
        builder.append("'}");
        return builder.toString();
    }
    
}
