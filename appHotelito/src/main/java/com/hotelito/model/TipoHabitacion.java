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
@Table(name = "tipo_habitacion")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TipoHabitacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_habitacion")
	private int idTipoHabitacion;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "precio")
	private double precio;
	
	
	public TipoHabitacion() {
		super();
	}

	public TipoHabitacion(int idTipoHabitacion, String titulo, String descripcion, double precio) {
		super();
		this.idTipoHabitacion = idTipoHabitacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public int getIdTipoHabitacion() {
		return idTipoHabitacion;
	}

	public void setIdTipoHabitacion(int idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	@Override
	public String toString() {
		return "TipoHabitacion [idTipoHabitacion=" + idTipoHabitacion + ", titulo=" + titulo + ", descripcion="
				+ descripcion + ", precio=" + precio + "]";
	}
	

}
