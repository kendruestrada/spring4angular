package com.tinet.common.representacion.error;

import java.io.Serializable;

public class Error  implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoError{VALIDACION, REGLA_NEGOCIO, DESCONOCIDO}

	private TipoError tipo;
	
	private String campoAsignado;

	private String[] camposAfectados;

	private String[] mensajes;

	private String[] sugerencias;
	
	
	
	public Error(TipoError tipo, String campoAsignado, String[] camposAfectados, String[] mensajes,
			String[] sugerencias) {
		super();
		this.tipo = tipo;
		this.campoAsignado = campoAsignado;
		this.camposAfectados = camposAfectados;
		this.mensajes = mensajes;
		this.sugerencias = sugerencias;
	}

	//se omiten getterd y setters

	public TipoError getTipo() {
		return tipo;
	}

	public void setTipo(TipoError tipo) {
		this.tipo = tipo;
	}

	public String getCampoAsignado() {
		return campoAsignado;
	}

	public void setCampoAsignado(String campoAsignado) {
		this.campoAsignado = campoAsignado;
	}

	public String[] getCamposAfectados() {
		return camposAfectados;
	}

	public void setCamposAfectados(String[] camposAfectados) {
		this.camposAfectados = camposAfectados;
	}

	public String[] getMensajes() {
		return mensajes;
	}

	public void setMensajes(String[] mensajes) {
		this.mensajes = mensajes;
	}

	public String[] getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(String[] sugerencias) {
		this.sugerencias = sugerencias;
	}
	
	
	
}
