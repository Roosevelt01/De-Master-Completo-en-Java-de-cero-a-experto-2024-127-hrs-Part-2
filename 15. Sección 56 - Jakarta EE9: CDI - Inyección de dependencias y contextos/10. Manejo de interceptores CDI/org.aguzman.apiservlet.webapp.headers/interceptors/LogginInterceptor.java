package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;


@Logging // 1. Marca esta clase como un Interceptor.

@Interceptor // 2. Enlaza este interceptor a la anotación @Logging.
public class LogginInterceptor {
    
    @inject
    private Logger log; 

    // 3. Define el método que interceptará las llamadas.
    @AroundInvoke
    public Object logging(InvocationContext invocation) throws Exception {
        // 4. Lógica ANTES de llamar al método original.
        log.info("***** Entrando antes de invocar el método "+
                invocation.getMethod().getName() +
                " de la clase " + invocation.getMethod().getDeclaringClass());

        // 5. Invoca el método original y captura su resultado.
        Object resultado = invocation.proceed();

        // 6. Lógica DESPUÉS de llamar al método original.
        log.info("***** Saliendo de la invocación del método "
                + invocation.getMethod().getName());

        // 7. Devuelve el resultado del método original.
        return  resultado;
    }
}
