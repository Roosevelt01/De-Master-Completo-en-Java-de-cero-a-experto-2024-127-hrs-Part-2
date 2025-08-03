package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.aguzman.apiservlet.webapp.headers.services.LoginService;
import org.aguzman.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // 1. Pedimos a CDI que nos inyecte la implementación activa de LoginService.
    @Inject
    private LoginService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
        // El código 'new LoginServiceSessionImpl()' se elimina.

        // 2. Usamos directamente el servicio 'auth' inyectado.
        Optional<String> username = auth.getUsername(req);
        if(username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
