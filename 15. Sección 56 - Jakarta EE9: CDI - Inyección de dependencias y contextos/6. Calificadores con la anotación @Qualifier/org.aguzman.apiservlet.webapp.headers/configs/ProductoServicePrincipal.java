package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;


// Calificador personalizado para identificar la implementación principal de ProductoService.
// Reemplaza el uso de @Named para una solución más segura y expresiva.
 
// 1. Le dice a CDI: "Esta anotación es un Calificador".
@Qualifier
// 2. Asegura que la anotación esté disponible en tiempo de ejecución para CDI.
@Retention(RetentionPolicy.RUNTIME)
// 3. Define que esta anotación se puede usar en clases, campos, métodos, etc.
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})
public @interface ProductoServicePrincipal {
}
