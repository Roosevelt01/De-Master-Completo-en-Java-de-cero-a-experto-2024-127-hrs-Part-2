package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.aguzman.apiservlet.webapp.headers.services.LoginService;
import org.aguzman.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Paso 1: Usar la implementación de servicio de login basada en sesiones
        LoginService auth = new LoginServiceSessionImpl();

        Optional<String> username = auth.getUsername(req);
        if(username.isPresent()){
<<<<<<< HEAD
            HttpSession session = req.getSession();/    
            session.invalidate(); 
=======
            // Paso 2: Obtener la sesión HTTP actual           
            HttpSession session = req.getSession();

            // Paso 3: Invalidar (eliminar) la sesión completa
            session.invalidate();
>>>>>>> 581d318f264a0bf058e41489f783fc8f2817b114
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
