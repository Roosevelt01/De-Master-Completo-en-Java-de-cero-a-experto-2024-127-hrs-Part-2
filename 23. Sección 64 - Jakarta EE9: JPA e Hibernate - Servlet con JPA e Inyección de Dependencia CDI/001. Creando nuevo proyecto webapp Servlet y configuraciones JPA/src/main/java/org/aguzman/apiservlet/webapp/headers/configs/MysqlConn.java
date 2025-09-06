package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//gemini tienes que conceptualizar todos eso(para que sirve y funcionalidad)
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})
public @interface MysqlConn {
}
