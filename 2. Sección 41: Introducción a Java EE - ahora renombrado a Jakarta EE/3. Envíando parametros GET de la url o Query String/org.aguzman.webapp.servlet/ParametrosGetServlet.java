package org.aguzman.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; // Necesaria para el objeto request
import jakarta.servlet.http.HttpServletResponse; // Necesaria para el objeto response

@WebServlet("/parametros/url-get") // Paso 1: Mapea el Servlet a esta ruta
public class ParametrosGetServlet extends HttpServlet {

    // Método para manejar peticiones HTTP GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        // Paso 2: Obtiene el valor del parámetro "saludo" de la Query String
        String saludo = req.getParameter("saludo");

        // Genera el código HTML de la respuesta
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("     <head>");
        out.println("         <meta charset=\"UTF-8\">");
        out.println("         <title>Parámetros Get de la url</title>"); // Título de la página actualizado
        out.println("     </head>");
        out.println("     <body>");
        out.println("         <h1>Parámetros Get de la url</h1>"); // Encabezado principal
        // Paso 3: Incluye el valor del parámetro 'saludo' en el HTML generado
        out.println("         <h2>El saludo enviado es: " + saludo + "</h2>"); 
        out.println("     </body>");
        out.println("</html>");
        out.close();

    }
}