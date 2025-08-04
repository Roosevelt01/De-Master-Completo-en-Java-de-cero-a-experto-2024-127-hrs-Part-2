package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource; // 1. Importamos la anotación @Resource
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class ProducerResources {

    // 2. Inyectamos el DataSource directamente desde el servidor usando su nombre JNDI.
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @Named("conn")
    private Connection beanConnection() throws NamingException, SQLException {
        // 3. Este código manual ya no es necesario
        // Context initContext = new InitialContext();
        // Context envContext  = (Context)initContext.lookup("java:/comp/env");
        // DataSource ds_old = (DataSource)envContext.lookup("jdbc/mysqlDB");
        
        // 4. Usamos el DataSource 'ds' inyectado por @Resource
        return ds.getConnection();
    }
}