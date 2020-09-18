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
@Table(name = "Menu")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Menu implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private int idMenu;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    public Menu() {
    }

    public Menu(int idMenu, String nombre, String descripcion) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{idMenu:'");
        builder.append(idMenu);
        builder.append("',nombre:'");
        builder.append(nombre);
        builder.append("',descripcion:'");
        builder.append(descripcion);
        builder.append("'}");
        return builder.toString();
    }
}
