package com.tinet.sistemareservas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinet.sistemareservas.entities.Reserva;
import com.tinet.sistemareservas.exceptions.SistemaReservaException;
import com.tinet.sistemareservas.repositories.ReservaRpository;
import com.tinet.sistemareservas.repositories.Salarepository;

@RestController
public class ReservasController {

	@Autowired
	ReservaRpository reservaRpository;

	@Autowired
	Salarepository salaRepository;
	
	@RequestMapping(value="/reservas/{id}", method=RequestMethod.GET)
	public Reserva obtenerReserva(@PathVariable long id){
		return reservaRpository.findOne(id);
	}

	@RequestMapping(value="/reservas", method=RequestMethod.GET)
	public List<Reserva> obtenerReservas(){
		return reservaRpository.findAll();
	}	
	
	@RequestMapping(value="/reservas", method=RequestMethod.POST)
	public void crearReserva(@RequestBody  @Valid Reserva r) throws SistemaReservaException{
		//si se topa con otra reserva 
		if(!reservaRpository.obtenerTraslapes(r.getFechaInicio(), r.getFechaFin()).isEmpty()){
			throw new SistemaReservaException();  
		 }
		 reservaRpository.save(r);
	}	

	@RequestMapping(value="/reservas/{id}", method=RequestMethod.DELETE)
	public void eliminarReserva(@PathVariable long id){
		 reservaRpository.delete(id);
		
	}	
	
	@RequestMapping(value="/reservas/{id}", method=RequestMethod.PUT)
	public void modificarReserva(@RequestBody  @Valid Reserva r) throws SistemaReservaException{
		//si se topa con otra reserva 
		if(!reservaRpository.obtenerTraslapes(r.getFechaInicio(), r.getFechaFin()).isEmpty()){
			throw new SistemaReservaException();  
		 }
		
		Reserva encontrado = reservaRpository.findOne(r.getId());
		encontrado.setFechaInicio(r.getFechaInicio());
		encontrado.setFechaFin(r.getFechaFin());
		encontrado.setSala(salaRepository.findOne(r.getSala().getId()));
		reservaRpository.save(encontrado);
	}	
}
