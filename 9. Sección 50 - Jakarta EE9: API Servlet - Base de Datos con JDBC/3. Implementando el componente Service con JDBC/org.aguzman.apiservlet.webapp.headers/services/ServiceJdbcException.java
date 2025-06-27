

package org.aguzman.apiservlet.webapp.headers.services;

public class ServiceJdbcException extends  RuntimeException{
    // Al heredar de RuntimeException, se convierte en una excepción "unchecked".
    public ServiceJdbcException(String message) {
        super(message);
    }

    // Este constructor es clave: permite "envolver" la excepción original (la causa).
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
