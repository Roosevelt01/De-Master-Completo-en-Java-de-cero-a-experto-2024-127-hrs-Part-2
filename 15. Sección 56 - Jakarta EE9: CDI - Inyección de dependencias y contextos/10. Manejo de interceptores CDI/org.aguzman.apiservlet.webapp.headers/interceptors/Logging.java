package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 1. Define esta anotación como un enlace para un interceptor.
@InterceptorBinding
// 2. Meta-anotaciones estándar para que esté disponible en tiempo de ejecución.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Logging {}


