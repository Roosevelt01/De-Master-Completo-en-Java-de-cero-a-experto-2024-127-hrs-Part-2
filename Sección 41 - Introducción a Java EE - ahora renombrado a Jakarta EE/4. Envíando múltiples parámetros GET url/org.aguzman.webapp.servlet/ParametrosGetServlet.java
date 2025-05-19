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
        out.print("     </body>");
        out.print("</html>");
        out.close();

    }
}