package com.tinet.common.representacion.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tinet.common.representacion.error.Error.TipoError;

public class ContenedorErrores implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Error> errores;

	public ContenedorErrores() {
		errores = new ArrayList<Error>();
	}

	public void addError(TipoError tipo, String campoAsignado, String[] camposAfectados, String[] mensajes,
			String[] sugerencias) {
		errores.add(new Error(tipo, campoAsignado, camposAfectados, mensajes, sugerencias));
	}

	// se omiten getters y setter

	public List<Error> getErrores() {
		return errores;
	}

	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}

}
