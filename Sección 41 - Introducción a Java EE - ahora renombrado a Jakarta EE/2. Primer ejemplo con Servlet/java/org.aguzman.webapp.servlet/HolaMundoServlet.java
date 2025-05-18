package org.aguzman.webapp.servlet;

import jakarta.servlet.annotation.WebServlet; // Importa la anotación @WebServlet
import jakarta.servlet.http.HttpServlet; // Importa la clase base para Servlets HTTP

@WebServlet("/hola-mundo") // Mapea este Servlet a la ruta /hola-mundo
public class HolaMundoServlet extends HttpServlet{
    
    // Método para manejar peticiones HTTP GET
    @Override // Indica que sobrescribimos el método de HttpServlet
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

        // --- Lógica para construir la respuesta ---

        // 1. Establecer el tipo de contenido de la respuesta
        response.setContentType("text/html"); // Indica al cliente que el contenido es HTML

        // 2. Obtener un escritor para el cuerpo de la respuesta
        PrintWriter out = response.getWriter(); 

        // 3. Escribir el cuerpo de la respuesta (código HTML en este caso)
        out.print("<!DOCTYPE html>"); 
        out.print("<html>");          
        out.print("     <head>");     
        out.print("         <meta charset=\"UTF-8\">"); 
        out.print("         <title>Hola Mundo Servlet</title>"); 
        out.print("     </head>");    
        out.print("     <body>");     
        out.print("         <h1>Hola Mundo</h1>"); 
        out.print("     </body>");    
        out.print("</html>");         

        // 4. Cerrar el escritor (opcional pero buena práctica)
        out.close(); 
    }
}



