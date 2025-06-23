package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 1. Anotación para mapear la URL
@WebServlet("/ver-carro")
// 2. Extender de HttpServlet
public class VerCarroServlet extends HttpServlet {

    // 3. Sobrescritura del método doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
        // 4. Despachar la solicitud a la vista JSP
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);
    }
}
