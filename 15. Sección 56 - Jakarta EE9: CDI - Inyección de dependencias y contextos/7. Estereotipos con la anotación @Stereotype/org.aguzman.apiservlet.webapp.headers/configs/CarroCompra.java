package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 1. Agrupamos las anotaciones que definen nuestro estereotipo
@SessionScoped// 1a. El bean será de scope de sesión.
@Named// 1b. El bean será nombrable para usar en EL.          
// 2. Definimos que esta anotación es un Estereotipo
@Stereotype
// 3. Meta-anotaciones estándar de Java
@Retention(RUNTIME)
@Target(ElementType.TYPE) // Solo se puede aplicar a nivel de clase.
public @interface CarroCompra {
}


