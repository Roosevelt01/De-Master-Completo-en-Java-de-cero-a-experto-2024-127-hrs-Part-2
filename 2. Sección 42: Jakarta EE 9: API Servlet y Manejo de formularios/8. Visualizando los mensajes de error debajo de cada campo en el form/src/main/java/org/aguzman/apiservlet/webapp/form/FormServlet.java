package org.aguzman.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null &&
                req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");

        // Paso 1: Cambia la declaración de una lista a un mapa para errores
        Map<String, String> errores = new HashMap<>();

        // 2. Validaciones de Campos y Adición de Errores al Mapa dentro cada if
        if (username == null || username.isBlank()) {
            errores.put("username","el usarname es requerido!");
        }

        if (password == null || password.isBlank()) {
            errores.put("password","el password no puede ser vacío!");
        }

        if (email == null || !email.contains("@")) {
            errores.put("email","el email es requerido! ");
        }

        if (pais == null || pais.equals("") || pais.equals(" ")) {
            errores.put("pais","el país es requerido!");
        }

        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes","debe seleccionar al menos un rol!");
        }

        if (roles == null  || roles.length == 0) {
            errores.put("roles","debe seleccionar un rol!");
        }

        if (idioma == null) {
            errores.put("idioma","debe seleccionar un idioma!");
        }

        if (errores.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Resultado form</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Resultado form!</h1>");

                out.println("          <ul>");

                out.println("              <li>Username: " + username + "</li>");
                out.println("              <li>Password: " + password + "</li>");
                out.println("              <li>Email: " + email + "</li>");
                out.println("              <li>País: " + pais + "</li>");
                out.println("              <li>Lenguajes: <ul>");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("                       <li>" + lenguaje + "</li>");
                });
                out.println("              </lu></li>");

                out.println("              <li>Roles: <ul>");
                Arrays.asList(roles).forEach(role -> {
                    out.println("                       <li>" + role + "</li>");
                });
                out.println("              </lu></li>");

                out.println("              <li>Idioma: " + idioma + "</li>");
                out.println("              <li>Habilitado: " + habilitar + "</li>");
                out.println("              <li>Secreto: " + secreto + "</li>");

                out.println("          </ul>");
                out.println("     </body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        }
    }
}
