package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.services.LoginService;
import org.aguzman.apiservlet.webapp.headers.services.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
<<<<<<< HEAD
        
        // Paso 1: Instanciar y usar LoginServiceImpl para obtener el nombre de usuario de la cookie.
        LoginServiceImpl auth = new LoginServiceImpl(); // Crea una instancia de LoginServiceImpl.
        Optional<String> cookieOptional = auth.getUsername(req); // Llama al método getUsername para buscar la cookie.
=======
        // Paso 1: Instanciar y usar LoginServiceImpl para obtener el nombre de usuario de la cookie.
        LoginService auth = new LoginServiceImpl(); // Crea una instancia de LoginServiceImpl.
        Optional<String> cookieOptional = auth.getUsername(req); // Llama al método getUsername para buscar la cookie.
>>>>>>> 110196b44e869a6019ab7bcf524010eb0c85150d

        if(cookieOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Hola " + cookieOptional.get() + "</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Hola " + cookieOptional.get() + " y has inciado sesión anteriormente! </h1>");
<<<<<<< HEAD
                //Paso 3: Añadir un enlace "Volver"
=======
                // Paso 2: Añadir un enlace "Volver"
>>>>>>> 110196b44e869a6019ab7bcf524010eb0c85150d
                out.println("<p><a href='"+req.getContextPath() +"/index.html'>Volver</a></p>");
                out.println("     </body>");
                out.println("</html>");
            }
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){

            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);

            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title></title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Login correcto!</h1>");
                out.println("         <h3>¡Hola " +username+ " has ingresado sesión con éxito!</h3>");
                // Paso 3: Añadir un enlace "Volver" si no se usa redirección
                out.println("<p><a href='"+req.getContextPath() +"/index.html'>Volver</a></p>");
                out.println("     </body>");
                out.println("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no está autorizado para ingresar a esta página");
        }
    }
}
