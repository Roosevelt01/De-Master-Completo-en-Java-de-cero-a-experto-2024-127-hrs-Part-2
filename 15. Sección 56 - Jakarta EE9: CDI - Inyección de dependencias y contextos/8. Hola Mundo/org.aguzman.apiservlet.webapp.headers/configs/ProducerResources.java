package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 1. Añadimos un scope para que CDI la reconozca en modo "annotated".
@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    //Paso 2: El MÉTODO DISPOSER limpia la conexión producida.
    public void close(@Disposes @MysqlConn Connection connection)
     throws SQLException {
        connection.close();
        System.out.println("Cerrando la conexión a la bbdd mysql");
    }
}