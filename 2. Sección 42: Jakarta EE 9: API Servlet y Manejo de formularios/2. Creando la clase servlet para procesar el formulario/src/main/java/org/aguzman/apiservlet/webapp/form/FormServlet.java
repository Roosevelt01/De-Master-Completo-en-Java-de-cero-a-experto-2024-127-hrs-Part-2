package org.aguzman.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter; // Necesario para escribir la respuesta

@WebServlet("/registro")// Anotación para mapear el Servlet a una URL
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Recupera los parámetros del formulario utilizando el atributo 'name' de los inputs
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // try-with-resources asegura que el PrintWriter se cierre automáticamente
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>Resultado form</title>");
            out.println("     </head>");
            out.println("     <body>");
            out.println("         <h1>Resultado form!</h1>");

            // Mostrar los parámetros recibidos en una lista desordenada (<ul>)
            out.println("          <ul>");
            out.println("              <li>Username: "+username+"</li>");
            out.println("              <li>Password: "+password+"</li>");
            out.println("              <li>Email: "+email+"</li>");
            out.println("          </ul>");

            out.println("     </body>");
            out.println("</html>");
        }// El 'out' se cierra automáticamente aquí gracias al try-with-resources

    }
}
