package org.aguzman.webapp.servlet;

import jakarta.servlet.annotation.WebServlet; // Importa la anotación @WebServlet
import jakarta.servlet.http.HttpServlet; // Importa la clase base para Servlets HTTP

@WebServlet("/hola-mundo") // Mapea este Servlet a la ruta /hola-mundo
public class HolaMundoServlet extends HttpServlet{
        // Método para manejar peticiones HTTP GET
    @Override // Indica que sobrescribimos el método de HttpServlet
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException /* Declaramos las excepciones que puede lanzar */ {

        // --- Lógica para construir la respuesta ---

        // 1. Establecer el tipo de contenido de la respuesta
        response.setContentType("text/html"); // Indica al cliente que el contenido es HTML

        // 2. Obtener un escritor para el cuerpo de la respuesta
        PrintWriter out = response.getWriter(); // Obtiene un objeto PrintWriter para escribir texto en el cuerpo de la respuesta

        // 3. Escribir el cuerpo de la respuesta (código HTML en este caso)
        out.print("<!DOCTYPE html>"); // Escribe la línea DOCTYPE HTML5
        out.print("<html>");          // Escribe la etiqueta de apertura <html>
        out.print("     <head>");     // Escribe la etiqueta de apertura <head>
        out.print("         <meta charset=\"UTF-8\">"); // Escribe la etiqueta meta para la codificación de caracteres UTF-8. Nota: se escapan las comillas dobles \"
        out.print("         <title>Hola Mundo Servlet</title>"); // Escribe la etiqueta <title> con el texto del título
        out.print("     </head>");    // Escribe la etiqueta de cierre </head>
        out.print("     <body>");     // Escribe la etiqueta de apertura <body>
        out.print("         <h1>Hola Mundo</h1>"); // Escribe un encabezado de nivel 1
        out.print("     </body>");    // Escribe la etiqueta de cierre </body>
        out.print("</html>");         // Escribe la etiqueta de cierre </html>

        // 4. Cerrar el escritor (opcional pero buena práctica)
        out.close(); // Cierra el stream del escritor. Esto también vuelca cualquier contenido restante del buffer y finaliza la respuesta.
}



