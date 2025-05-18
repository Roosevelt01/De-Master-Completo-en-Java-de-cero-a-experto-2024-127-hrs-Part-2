package org.aguzman.webapp.servlet;

import jakarta.servlet.annotation.WebServlet; // Importa la anotación @WebServlet
import jakarta.servlet.http.HttpServlet; // Importa la clase base para Servlets HTTP

@WebServlet("/hola-mundo") // Mapea este Servlet a la ruta /hola-mundo
public class HolaMundoServlet extends HttpServlet{
    // ... cuerpo de la clase ...
}



