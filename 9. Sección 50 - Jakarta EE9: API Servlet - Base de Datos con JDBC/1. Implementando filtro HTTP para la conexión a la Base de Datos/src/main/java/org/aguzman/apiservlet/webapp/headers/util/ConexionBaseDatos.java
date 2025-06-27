

package org.aguzman.apiservlet.webapp.headers.util;

import java.sql.*;

//Clase de utilidad para gestionar la creación de conexiones a la base de datos.
//Centraliza los parámetros de conexión (URL, usuario, contraseña).

public class ConexionBaseDatos {
    // Parámetros de la conexión como atributos estáticos privados.
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Santiago";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws SQLException {
        // Se utiliza el DriverManager para obtener la conexión usando los parámetros definidos.
        return DriverManager.getConnection(url, username, password);
    }

}


