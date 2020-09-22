package com.hotelito.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "promocion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Promocion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promocion")
    private int idPromocion;
	@Column(name = "nombre_promocion")
	private String nombrePromocion;
    @Column(name = "descuento")
    private double descuento;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_inicio_prom")
    private Date fechaIinicioProm;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_fin_prom")
    private Date fechaFinProm;
	public int getIdPromocion() {
		return idPromocion;
	}
	public void setIdPromocion(int idPromocion) {
		this.idPromocion = idPromocion;
	}
	public String getNombrePromocion() {
		return nombrePromocion;
	}
	public void setNombrePromocion(String nombrePromocion) {
		this.nombrePromocion = nombrePromocion;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public Date getFechaIinicioProm() {
		return fechaIinicioProm;
	}
	public void setFechaIinicioProm(Date fechaIinicioProm) {
		this.fechaIinicioProm = fechaIinicioProm;
	}
	public Date getFechaFinProm() {
		return fechaFinProm;
	}
	public void setFechaFinProm(Date fechaFinProm) {
		this.fechaFinProm = fechaFinProm;
	}
	@Override
	public String toString() {
		return "Promocion [idPromocion=" + idPromocion + ", nombrePromocion=" + nombrePromocion + ", descuento="
				+ descuento + ", fechaIinicioProm=" + fechaIinicioProm + ", fechaFinProm=" + fechaFinProm + "]";
	}

}
