package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;// Importa la clase Cookie para crear y manipular cookies.
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;// Para trabajar con el Stream API de Java 8+ sobre arrays.
import java.util.Optional;// Para manejar de forma segura la posible ausencia de una cookie.

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener las cookies de la solicitud del cliente
        Cookie[] cookies = req.getCookies() != null? req.getCookies():new Cookie[0];

        //Buscar la cookie de "username" usando Java Streams
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();

        if(cookieOptional.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                // Muestra el nombre de usuario obtenido de la cookie en el título de la página.
                out.println("         <title>Hola " + cookieOptional.get() + "</title>");
                out.println("     </head>");
                out.println("     <body>");
                // Muestra un mensaje de bienvenida en el cuerpo de la página, indicando que ya ha iniciado sesión.
                out.println("         <h1>Hola " + cookieOptional.get() + " y has inciado sesiónanteriormente! </h1>");
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

            // Si las credenciales son correctas:
            // Paso 1: Crear una nueva cookie
            // Se instancia un objeto Cookie con el nombre "username" 
            // y el valor del nombre de usuario que inició sesión.
            Cookie usernameCookie = new Cookie("username", username);

            // Paso 2: Añadir la cookie a la respuesta HTTP
            // resp.addCookie(usernameCookie): Agrega la cookie al encabezado 'Set-Cookie'
            //  de la respuesta HTTP.
            
            // Cuando el navegador reciba esta respuesta, almacenará esta cookie.
            // En solicitudes futuras al mismo dominio,
            // el navegador enviará esta cookie automáticamente.
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

                out.print("     </body>");
                out.print("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no está autorizado para ingresar a esta página");
        }
    }
}
