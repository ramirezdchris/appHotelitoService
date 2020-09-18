package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Usuario_Empleado")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UsuarioEmpleado implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(nullable = true, name = "id_usuario_empleado")
    private Personal id_usuario_empleado;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;

    public UsuarioEmpleado() {
    }

    public UsuarioEmpleado(Personal id_usuario_empleado, String usuario, String clave) {
        this.id_usuario_empleado = id_usuario_empleado;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Personal getId_usuario_empleado() {
        return id_usuario_empleado;
    }

    public void setId_usuario_empleado(Personal id_usuario_empleado) {
        this.id_usuario_empleado = id_usuario_empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id_usuario_empleado:'");
        builder.append(id_usuario_empleado);
        builder.append("',usuario:'");
        builder.append(usuario);
        builder.append("',clave:'");
        builder.append(clave);
        builder.append("'}");
        return builder.toString();
    }
    
}
