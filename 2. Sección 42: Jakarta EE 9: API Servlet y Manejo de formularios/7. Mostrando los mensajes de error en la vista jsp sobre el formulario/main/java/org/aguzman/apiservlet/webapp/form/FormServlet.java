package org.aguzman.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<String> errores = new ArrayList<>();

        // Paso 2: Validaciones de Campos
        if (username == null || username.isBlank()) {
            errores.add("el usarname es requerido!");
        }

        if (password == null || password.isBlank()) {
            errores.add("el password no puede ser vacío!");
        }

        if (email == null || !email.contains("@")) {
            errores.add("el email es requerido! ");
        }

        if (pais == null || pais.equals("") || pais.equals(" ")) {
            errores.add("el país es requerido");
        }

        if (lenguajes == null || lenguajes.length == 0) {
            errores.add("debe seleccionar al menos un rol!");
        }

        if (roles == null  || roles.length == 0) {
            errores.add("debe seleccionar un rol!");
        }

        if (idioma == null) {
            errores.add("debe seleccionar un idioma!");
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
        } else {//Paso 1: Si hay errores, los agregamos como atributo al request usando setAttribute
            // Si SÍ hay errores (validación fallida)
            // Se adjunta la lista de errores al request scope
            req.setAttribute("errores", errores);
            // Se reenvía la solicitud de vuelta a index.jsp para mostrar el formulario con los errores
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
