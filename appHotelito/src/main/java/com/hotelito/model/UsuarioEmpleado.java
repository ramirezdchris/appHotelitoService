package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Usuario_Empleado")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UsuarioEmpleado implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_empleado")
    private int id_usuario_empleado;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "clave")
    private String clave;
    @ManyToOne
    @JoinColumn(name = "personal")
    private Personal personal;

    public UsuarioEmpleado() {
    }

    public UsuarioEmpleado(int id_usuario_empleado, String usuario, String clave, Personal personal) {
		super();
		this.id_usuario_empleado = id_usuario_empleado;
		this.usuario = usuario;
		this.clave = clave;
		this.personal = personal;
	}

	public int getId_usuario_empleado() {
		return id_usuario_empleado;
	}



	public void setId_usuario_empleado(int id_usuario_empleado) {
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



	public Personal getPersonal() {
		return personal;
	}



	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	@Override
	public String toString() {
		return "UsuarioEmpleado [id_usuario_empleado=" + id_usuario_empleado + ", usuario=" + usuario + ", clave="
				+ clave + ", personal=" + personal + "]";
	}
  
}
