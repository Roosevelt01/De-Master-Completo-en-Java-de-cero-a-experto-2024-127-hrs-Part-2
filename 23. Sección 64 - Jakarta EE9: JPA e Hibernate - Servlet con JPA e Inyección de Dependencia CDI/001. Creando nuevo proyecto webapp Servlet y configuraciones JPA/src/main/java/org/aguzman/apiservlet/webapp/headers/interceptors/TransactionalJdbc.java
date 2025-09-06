package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding //Paso 1
@Retention(RetentionPolicy.RUNTIME) //Paso 2
@Target({ElementType.METHOD, ElementType.TYPE}) //Paso 3
public @interface TransactionalJdbc {
}
