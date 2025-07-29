package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/carro/ver")
public class VerCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Paso 1: Concatena el título por defecto con el título específico de la vista
        req.setAttribute("title", req.getAttribute("title") + ": Carro de compras");//Paso 1

        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);

    }
}
