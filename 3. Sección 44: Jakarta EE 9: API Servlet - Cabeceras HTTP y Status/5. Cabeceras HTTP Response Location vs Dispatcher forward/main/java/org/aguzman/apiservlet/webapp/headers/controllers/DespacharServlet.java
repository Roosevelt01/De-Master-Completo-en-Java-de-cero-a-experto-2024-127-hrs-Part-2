package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/despachar")
public class DespacharServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        // Obtener el RequestDispatcher para el recurso de destino.
        // La ruta es relativa al contexto de la aplicación, sin el nombre del contexto.
        // Es la ruta interna del Servlet o JSP (ej., "/productos.html").
        // Luego, se "reenvía" (forward) la petición y respuesta actuales al nuevo recurso.
        getServletContext().getRequestDispatcher("/productos.html").forward(req, resp);
    }
}
