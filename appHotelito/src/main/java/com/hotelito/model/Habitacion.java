package com.hotelito.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "habitacion")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Habitacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_habitacion")
	private int idHabitacion;
	@Column(name = "nombre_habitacion")
	private String nombreHabitacion;
	@Column(name ="estado")
	private int estadoHabitacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="tipo_habitacion")
	private TipoHabitacion tipoHabitacion;	
	
	public Habitacion() {
		super();
	}


	public Habitacion(int idHabitacion, String nombreHabitacion, int estadoHabitacion,
			TipoHabitacion tipoHabitacion) {
		super();
		this.idHabitacion = idHabitacion;
		this.nombreHabitacion = nombreHabitacion;
		this.estadoHabitacion = estadoHabitacion;
		this.tipoHabitacion = tipoHabitacion;
	}


	public int getIdHabitacion() {
		return idHabitacion;
	}


	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}


	public String getNombreHabitacion() {
		return nombreHabitacion;
	}


	public void setNombreHabitacion(String nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}


	public int getEstadoHabitacion() {
		return estadoHabitacion;
	}


	public void setEstadoHabitacion(int estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}


	public TipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}


	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}


	@Override
	public String toString() {
		return "Habitacion [idHabitacion=" + idHabitacion + ", nombreHabitacion=" + nombreHabitacion
				+ ", estadoHabitacion=" + estadoHabitacion + ", tipoHabitacion=" + tipoHabitacion + "]";
	}

	
}
