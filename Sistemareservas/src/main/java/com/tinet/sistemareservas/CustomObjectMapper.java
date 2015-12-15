package com.tinet.sistemareservas;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper  extends ObjectMapper {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
        super();
        //  setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXX"));
    }
}
