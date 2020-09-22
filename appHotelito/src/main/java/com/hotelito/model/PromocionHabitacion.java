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

@Entity
@Table(name = "prom_hab")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PromocionHabitacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prom_hab")
	private int idPromHab;
	@ManyToOne
    @JoinColumn(name = "habitacion")
    private Habitacion Habitacion;
    @ManyToOne
    @JoinColumn(name = "promocion")
    private Promocion Promocion;
	public int getIdPromHab() {
		return idPromHab;
	}
	public void setIdPromHab(int idPromHab) {
		this.idPromHab = idPromHab;
	}
	public Habitacion getHabitacion() {
		return Habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		Habitacion = habitacion;
	}
	public Promocion getPromocion() {
		return Promocion;
	}
	public void setPromocion(Promocion promocion) {
		Promocion = promocion;
	}
	@Override
	public String toString() {
		return "PromocionHabitacion [idPromHab=" + idPromHab + ", Habitacion=" + Habitacion + ", Promocion=" + Promocion
				+ "]";
	}
	
}
