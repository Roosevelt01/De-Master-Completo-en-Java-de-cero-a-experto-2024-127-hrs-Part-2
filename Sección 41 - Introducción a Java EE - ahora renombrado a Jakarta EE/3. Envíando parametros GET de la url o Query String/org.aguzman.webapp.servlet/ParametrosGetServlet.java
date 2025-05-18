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
        // Implementación del método doGet irá aquí
    }
}