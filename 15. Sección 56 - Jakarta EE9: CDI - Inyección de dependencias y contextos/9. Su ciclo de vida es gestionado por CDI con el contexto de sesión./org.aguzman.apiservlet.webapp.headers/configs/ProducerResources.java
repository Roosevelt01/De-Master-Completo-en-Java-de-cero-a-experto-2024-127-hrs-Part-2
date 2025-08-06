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

// Paso 1: Define esta clase como un bean de alcance de aplicación (un Singleton).
// Esto asegura que CDI la reconozca y procese sus métodos productores/disposers,
// especialmente cuando se usa bean-discovery-mode="annotated" en beans.xml.
@ApplicationScoped
public class ProducerResources {

    // Paso 1: Define esta clase como un bean de alcance de aplicación (un Singleton).
    // Esto asegura que CDI la reconozca y procese sus métodos productores/disposers,
    // especialmente cuando se usa bean-discovery-mode="annotated" en beans.xml.
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

    // Paso 3: Este es un método productor "inteligente" para crear Loggers.
    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return  Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        //System.out.println("Cerrando la conexión a la bbdd mysql");
        // Paso 4: Utiliza el Logger inyectado para registrar el evento de cierre.
        // A diferencia de System.out.println, esto crea un registro estructurado con
        // fecha, nivel (INFO) y el nombre de la clase de origen, lo cual es una mejor práctica.
        log.info("Cerrando la conexión a la bbdd mysql");
    }
}
