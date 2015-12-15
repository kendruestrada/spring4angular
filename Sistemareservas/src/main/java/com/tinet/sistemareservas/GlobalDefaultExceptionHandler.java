package com.tinet.sistemareservas;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tinet.common.representacion.error.ContenedorErrores;
import com.tinet.common.representacion.error.Error.TipoError;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
	
	
	
    public static final String DEFAULT_ERROR_VIEW = "error";

    private MessageSource messageSource;
    @Autowired
    public GlobalDefaultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    /**
     * Maneja Excepciones conocidas (chequeadas) 
     */
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ContenedorErrores  defaultErrorHandler(HttpServletRequest req,HttpServletResponse res, Exception e) throws Exception {
    	int status = 500;
    	//se envia el estado Http que tiene anotada la excepcion si no se encuentra la aonotacion se envia 500
    	if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
    		ResponseStatus r  = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
    		status = ((HttpStatus)AnnotationUtils.getValue(r)).value();
    	}
    	ContenedorErrores errores = new ContenedorErrores();
    	errores.addError(TipoError.REGLA_NEGOCIO, e.getMessage(), new String[0], new String[0], new String[0]);
    	res.setStatus(status);
        return  errores;
    }
    
    
    /**
     * Maneja Errores de validacion 
     */    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ContenedorErrores processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    }  
    
    private ContenedorErrores processFieldErrors(List<FieldError> fieldErrors) {
    	ContenedorErrores dto = new ContenedorErrores();
 
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addError(TipoError.VALIDACION, fieldError.getField(), new String[0], new String[]{localizedErrorMessage}, new String[0]);
        }
        return dto;
    }    
    
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
 
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }
 
        return localizedErrorMessage;
    }    
    
}
