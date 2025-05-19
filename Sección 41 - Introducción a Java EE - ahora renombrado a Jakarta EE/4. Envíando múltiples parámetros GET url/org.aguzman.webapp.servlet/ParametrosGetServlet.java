package org.aguzman.webapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/parametros/url-get") 
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre"); // Paso 1: Obtiene el parámetro 'nombre'

        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("     <head>");
        out.print("         <meta charset=\"UTF-8\">");
        out.print("         <title>Parámetros Get de la url</title>");
        out.print("     </head>");
        out.print("     <body>");
        out.print("         <h1>Parámetros Get de la url</h1>");
        
        // Paso 2: Lógica condicional basada en la presencia de parámetros
        if(saludo!=null && nombre !=null){
             // Si ambos parámetros están presentes
            out.print("         <h2>El saludo enviado es: "+saludo+" "+nombre+"</h2>");
        }else if(saludo!=null){
            // Si solo el parámetro 'saludo' está presente
            out.print("         <h2>El saludo enviado es: "+saludo+"</h2>");
        } else if (nombre != null) {
            // Si solo el parámetro 'nombre' está presente
            out.print("         <h2>Hola mi nombre es "+nombre+"</h2>");
        } else {
            // Si ninguno de los parámetros esperados está presente
            out.print("<h2>No se han pasado los parámetros saludos ni nombre</h2>");
        }

        // Paso 3: Manejo de parámetro numérico con try-catch
        try {
            // Intenta obtener el parámetro "codigo" y convertirlo a Integer
            // req.getParameter("codigo") podría devolver null si el parámetro no está
            Integer codigo = Integer.valueOf(req.getParameter("codigo"));
            // Si la conversión tiene éxito, imprime el código
            out.println("<h3>El código enviado es: " + codigo + "</h3>");

        } catch (NumberFormatException e) {
            // Si ocurre NumberFormatException (parámetro no presente o no es un número)
            // Imprime un mensaje alternativo
            out.println("<h3>El código no se ha enviado o es inválido (no es un número)</h3>");
             // Nota: El mensaje original de la transcripción era "en null", lo cual es incorrecto.
             // La excepción ocurre porque es null O no es un número.
        }

        out.print("     </body>");
        out.print("</html>");
        out.close();

    }
}