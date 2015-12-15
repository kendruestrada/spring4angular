package com.tinet.sistemareservas.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@ManyToOne
	private Sala sala;
	
	@NotNull
	@Future
	private Date fechaInicio;

	@NotNull
	@Future
	private Date fechaFin;


	//Para validar varios campos esta no es la mejor solucion, para ver la solucion recomendada
	// http://stackoverflow.com/questions/1972933/cross-field-validation-with-hibernate-validator-jsr-303
     @AssertTrue public boolean isvalidRange() {
	    if(fechaInicio != null && fechaFin != null){
	    	return fechaFin.compareTo(fechaInicio) >= 0;
	    }
	    return true;
	 }	

	//Se omiten getters y setters por simplicidad
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
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


}
