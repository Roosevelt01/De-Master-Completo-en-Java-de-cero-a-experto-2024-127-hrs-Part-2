package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.aguzman.apiservlet.webapp.headers.interceptors.Logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApplicationScoped // 1. Define el scope.
@Logging         // 2. Aplica el interceptor de logging.
@Stereotype
@Named
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
