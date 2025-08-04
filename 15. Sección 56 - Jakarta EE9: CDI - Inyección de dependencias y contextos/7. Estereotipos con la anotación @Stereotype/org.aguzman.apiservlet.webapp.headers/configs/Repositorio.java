package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped // El bean será un Singleton.
@Named           // Le da un nombre por defecto (aunque no lo usemos activamente).
@Stereotype
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Repositorio {
}
