package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging//Paso 1
@Interceptor //Paso 2
public class LogginInterceptor {
    //Paso 3
    @Inject//Paso 4
    private Logger log; // Paso 5

    //Paso: 6
    @AroundInvoke
    public Object logging(InvocationContext invocation) throws Exception {
        //Paso 7
        log.info("***** Entrando antes de invocar el método "+
                invocation.getMethod().getName() +
                " de la clase " + invocation.getMethod().getDeclaringClass());

        // Paso 8
        Object resultado = invocation.proceed();

        log.info("***** Saliendo de la invocación del método "
                + invocation.getMethod().getName());

        //Paso 9
        return  resultado;

    }



}
