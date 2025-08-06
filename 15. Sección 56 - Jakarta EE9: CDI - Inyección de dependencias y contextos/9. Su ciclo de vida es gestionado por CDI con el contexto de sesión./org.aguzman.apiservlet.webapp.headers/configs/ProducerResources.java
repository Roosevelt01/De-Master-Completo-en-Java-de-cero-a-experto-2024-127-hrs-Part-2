package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ApplicationScoped//Paso 1
public class ProducerResources {

    @Inject
    private Logger log;


    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    //Paso 1: Gemini, ese tema del logger es nuevo para mí, necesito concepto, cual es la funcionalidad y casos de usos
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return  Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        //System.out.println("Cerrando la conexión a la bbdd mysql");
        log.info("Cerrando la conexión a la bbdd mysql");//Paso 3

    }
}
