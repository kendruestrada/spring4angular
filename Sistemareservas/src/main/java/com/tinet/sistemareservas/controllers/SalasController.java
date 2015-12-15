package com.tinet.sistemareservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinet.sistemareservas.entities.Sala;
import com.tinet.sistemareservas.repositories.Salarepository;

@RestController
public class SalasController {

	@Autowired
	Salarepository salaRepository;
	
	@RequestMapping(value="/salas", method=RequestMethod.GET)
	public List<Sala> obtenerSalas(){
		return salaRepository.findAll();
	}	
	
}
