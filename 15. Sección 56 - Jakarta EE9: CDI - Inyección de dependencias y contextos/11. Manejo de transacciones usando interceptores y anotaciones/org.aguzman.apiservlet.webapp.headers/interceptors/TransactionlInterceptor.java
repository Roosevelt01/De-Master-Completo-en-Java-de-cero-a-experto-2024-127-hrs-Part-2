package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.aguzman.apiservlet.webapp.headers.configs.MysqlConn;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

//Interceptor para gestionar transacciones de base de datos.
//Aplica commit o rollback a la conexión JDBC según el resultado
//de la invocación del método.

@TransactionalJdbc
@Interceptor
public class TransactionlInterceptor {

    @Inject 
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {
        // La transacción solo se maneja si el autocommit está habilitado
        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }

        try{
            log.info("------> Iniciando trancción "+ invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());
            // 1. Invoca el método original del servicio
            Object resultado = invocation.proceed();

            // 2. Si el método se ejecutó sin errores, hace commit
            conn.commit();
            
            log.info("------> Iniciando commit trancción "+ invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            // 3. Devuelve el resultado del método original
           return resultado;
        }catch(ServiceJdbcException e){
            // 4. Si hay un error, hace rollback y relanza la excepción
            conn.rollback();
            throw e;
        }

    }



}
