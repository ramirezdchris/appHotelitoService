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
@Table(name = "cuenta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cuenta implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private int idCuenta;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio idServicio;
    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva idReserva;

    public Cuenta() {
    }
    
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", idServicio=" + idServicio + ", idReserva=" + idReserva + '}';
    }

}
