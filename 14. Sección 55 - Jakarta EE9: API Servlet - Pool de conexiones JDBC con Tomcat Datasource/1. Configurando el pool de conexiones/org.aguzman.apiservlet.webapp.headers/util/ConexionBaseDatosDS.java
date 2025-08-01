package org.aguzman.apiservlet.webapp.headers.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatosDS {

    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = null;
        
        // 1. Obtener el contexto inicial de JNDI
        initContext = new InitialContext();
        // 2. Buscar el contexto del entorno de la aplicación
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        // 3. Buscar el DataSource por su nombre JNDI
        DataSource ds = (DataSource)envContext.lookup("jdbc/mysqlDB");
        // 4. Pedir una conexión al pool
        return ds.getConnection();

    }

}
