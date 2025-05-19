package org.aguzman.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/parametros/url-get") 
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre"); // Paso 1: Obtiene el parámetro 'nombre'

         // ... resto del código ...

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("     <head>");
        out.println("         <meta charset=\"UTF-8\">");
        out.println("         <title>Parámetros Get de la url</title>"); 
        out.println("     </head>");
        out.println("     <body>");
        out.println("         <h1>Parámetros Get de la url</h1>");
        out.println("         <h2>El saludo enviado es: " + saludo + "</h2>"); 
        out.println("     </body>");
        out.println("</html>");
        out.close();

    }
}