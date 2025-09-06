package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.aguzman.apiservlet.webapp.headers.configs.MysqlConn;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJdbc//Paso 1
@Interceptor//Paso 1
public class TransactionlInterceptor {

    @Inject //Paso 1
    @MysqlConn//Paso 2
    private Connection conn;//Paso 3

    @Inject//Paso 4
    private Logger log;//Paso 5

    @AroundInvoke//Paso 6
    public Object transactional(InvocationContext invocation) throws Exception {
        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }

        try{
            log.info("------> Iniciando trancción "+ invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());
            Object resultado = invocation.proceed();
            conn.commit();
            log.info("------> Iniciando commit trancción "+ invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

           return resultado;
        }catch(ServiceJdbcException e){
            conn.rollback();
            throw e;
        }

    }



}
