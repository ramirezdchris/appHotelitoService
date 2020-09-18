package com.hotelito.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "menu_dia")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class MenuDia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu_dia")
    private int idMenuDia;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;
    @ManyToOne    
    @JoinColumn(name = "menu")
    private Menu menu;

    public MenuDia() {
    }

    public MenuDia(int idMenuDia, Date fecha, Menu menu) {
		super();
		this.idMenuDia = idMenuDia;
		this.fecha = fecha;
		this.menu = menu;
	}

	public int getIdMenuDia() {
		return idMenuDia;
	}

	public void setIdMenuDia(int idMenuDia) {
		this.idMenuDia = idMenuDia;
	}

	public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id_menu_dia:'");
        builder.append(idMenuDia);
        builder.append("',fecha:'");
        builder.append(fecha);
        builder.append("',menu:'");
        builder.append(menu);
        builder.append("'}");
        return builder.toString();
    }
    
}
