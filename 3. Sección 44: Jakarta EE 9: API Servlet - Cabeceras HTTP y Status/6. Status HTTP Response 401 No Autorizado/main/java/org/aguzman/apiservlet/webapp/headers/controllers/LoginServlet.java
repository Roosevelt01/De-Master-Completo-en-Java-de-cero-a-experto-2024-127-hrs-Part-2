package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Definimos credenciales fijas para fines de demostración.
    // IMPORTANTE: En una aplicación de producción, las credenciales nunca deben estar
    // "hardcodeadas" (escritas directamente en el código) y siempre deben ser gestionadas
    // de forma segura (ej., almacenadas con hashing en una base de datos y verificadas
    // mediante un sistema de autenticación robusto).
    
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2. Validar las credenciales.
        // Se utiliza el método `equals()` para comparar el contenido de las cadenas de texto.
        // Es crucial usar `equals()` en lugar de `==` para comparar objetos String en Java,
        // ya que `==` solo compara si las referencias a los objetos son las mismas, no su contenido.
        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title></title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Login correcto!</h1>");
                out.println("         <h3>¡Hola " +username+ " has ingresado sesión con éxito!</h3>");

                out.print("     </body>");
                out.print("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no está autorizado para ingresar a esta página");
        }
    }
}
