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
        // Paso 11: Convertir el parámetro 'habilitar' de String a boolean
        boolean habilitar = req.getParameter("habilitar") != null &&
                req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");

        //Paso 1: Declaración de la lista para almacenar mensajes de error de validación
        List<String> errores = new ArrayList<>();

        //Paso 2: Validación del campo 'username'
        if( username== null || username.isBlank()){
            errores.add("el usarname es requerido!");
        }

        //Paso 3: Validación del campo 'password'
        if( password == null || password.isBlank()){
            errores.add("el password no puede ser vacío!");
        }

        //Paso 4: Validación del campo 'email'
        if(email == null || !email.contains("@")){
            errores.add("el país es requerido! ");
        }

        //Paso 5: Validación del campo 'pais'
        if(pais== null || pais.equals("") || pais.equals(" ")){
            errores.add("el país es requerido");
        }

        //PAso 6: Validación del campo 'lenguajes' (select múltiple)
        if(lenguajes == null || lenguajes.length == 0){
            errores.add("debe seleccionar al menos un rol!");
        }

        // Paso 7: Validación del campo 'roles' (checkboxes)
        if (roles == null || roles.length == 0) {
            errores.add("Debe seleccionar al menos un rol!");
        }

        //Paso 8: Validación del campo 'idioma' (radio button)
        if(idioma == null || idioma.isBlank()){
            errores.add("debe seleccionar un idioma!");
        }

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
            
            // Paso 9: Lógica condicional para mostrar errores o datos del formulario
            if(errores.isEmpty()) {// Si la lista de errores está vacía, la validación fue exitosa
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
            }else {// Si hay errores, se muestran los mensajes de validación
                errores.forEach(error -> {
                    out.println("<li>" + error + "</li>");
                });
                // Paso 10: Añadir un enlace para volver al formulario
                out.println("<p><a href=\"/webapp-form/index.html\">volver</a><p>");
            }
            out.println("          </ul>");
            out.println("     </body>");
            out.println("</html>");
        }

    }
}
