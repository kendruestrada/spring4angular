package com.tinet.sistemareservas.exceptions;


public class SistemaReservaException extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Error de Regla de negocio";
	}
	
	
	
}
