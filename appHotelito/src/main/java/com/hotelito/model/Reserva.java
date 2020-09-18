package com.hotelito.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reserva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private int idReserva;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(nullable = true, name = "total")
    private Double total;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;
    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion idHabitacion;
    @ManyToOne
    @JoinColumn(name = "id_promocion")
    private Promocion idPromocion;
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoReserva idEstado;

    public Reserva() {
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Habitacion getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Habitacion idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Promocion getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Promocion idPromocion) {
        this.idPromocion = idPromocion;
    }

    public EstadoReserva getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoReserva idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", total=" + total + ", idCliente=" + idCliente + ", idHabitacion=" + idHabitacion + ", idPromocion=" + idPromocion + ", idEstado=" + idEstado + '}';
    }

}
