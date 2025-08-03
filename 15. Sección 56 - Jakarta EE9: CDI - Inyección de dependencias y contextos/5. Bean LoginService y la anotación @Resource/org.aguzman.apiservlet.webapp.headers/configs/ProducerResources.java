package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
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

    //Paso 1
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces
    @RequestScoped
    @Named("conn")
    private Connection beanConnection() throws NamingException, SQLException {
        //Paso 2: Gemini documenta por que se comenta estar líneas de código
        // Context initContext = new InitialContext();
        // Context envContext  = (Context)initContext.lookup("java:/comp/env");
        // DataSource ds = (DataSource)envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }
}
