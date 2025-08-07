package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*Anotación de enlace que activa el TransactionalInterceptor.
Marca un metodo o clase para que se ejecute dentro de una transacción JDBC.*/

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TransactionalJdbc {
}


