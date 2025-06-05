package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
            // Opción 1 (Manual): Establecer la cabecera Location y el estado HTTP
            //resp.setHeader("Location", req.getContextPath()+ "/productos.html");
            //resp.setStatus(HttpServletResponse.SC_FOUND);

             // Opción 2 (Recomendada): Usar el método sendRedirect()
            resp.sendRedirect(req.getContextPath()+ "/productos.html");
    }
}
