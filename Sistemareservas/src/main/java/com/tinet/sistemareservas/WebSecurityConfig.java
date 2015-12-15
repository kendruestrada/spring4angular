package com.tinet.sistemareservas;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
      .and()
        .authorizeRequests()
            //se permiten ingresar sin autorizacion a los siguientes html's
            //i a todos los archivos javascript
          .antMatchers("/src/app/index.html", "/**/*.js", "/src/app/login/login.html").permitAll()
          //esto se debe hacer ya que Spring-security tine integrada la seguridad
          //contra atques del tipo CSRF, basicamente en cada solicitud se envia un token de verificacion
          // si no se recive el mismo toquen 
          .anyRequest().authenticated().and().csrf()
			.csrfTokenRepository(csrfTokenRepository()).and()
			.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    private CsrfTokenRepository csrfTokenRepository() {
    	  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    	  repository.setHeaderName("X-XSRF-TOKEN");
    	  return repository;
    }
    
    

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new CustomObjectMapper();
    }
    
    

    
}
