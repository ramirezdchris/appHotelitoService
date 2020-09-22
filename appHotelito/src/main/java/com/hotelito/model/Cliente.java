package com.hotelito.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Cliente implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private int idCliente;
	@Column(name = "nombres")
	private String nombre;
	@Column(name = "apellidos")
	private String apellido;
	@Column(name = "correo")
	private String correo;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "clave")
	private String clave;
	@Column(name = "estado")
	private String estado;
	
	
	public Cliente() {
		super();
	}

	public Cliente(int idCliente, String nombre, String apellido, String correo, String usuario, String clave, String estado) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.usuario = usuario;
		this.clave = clave;
		this.estado = estado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", correo="
				+ correo + ", usuario=" + usuario + ", clave=" + clave + ", estado=" + estado + "]";
	}

	
}
