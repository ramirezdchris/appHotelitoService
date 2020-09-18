package com.hotelito.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "estado_reserva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstadoReserva implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_reserva")
    private int idEstadoReserva;
    @Column(name = "descripcion")
    private String descripcion;

    public int getIdEstadoReserva() {
        return idEstadoReserva;
    }

    public void setIdEstadoReserva(int idEstadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EstadoReserva{" + "idEstadoReserva=" + idEstadoReserva + ", descripcion=" + descripcion + '}';
    }
    

}
