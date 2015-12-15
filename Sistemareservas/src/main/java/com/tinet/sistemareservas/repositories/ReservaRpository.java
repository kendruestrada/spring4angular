package com.tinet.sistemareservas.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tinet.sistemareservas.entities.Reserva;

public interface ReservaRpository extends JpaRepository<Reserva, Long> {

	//una explicacion brillante  de pq esta consulta funciona
	// http://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
	@Query("select r from Reserva r where (?1 < r.fechaFin) and (?2 > r.fechaInicio)")
	public List<Reserva> obtenerTraslapes(Date fechaInicio, Date fechaFin);
	
}
