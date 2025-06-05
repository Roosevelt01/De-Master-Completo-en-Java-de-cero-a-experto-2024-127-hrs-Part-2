package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/hora-actualizada")
public class HoraActualizadaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Paso 1: Configurar el tipo de contenido de la respuesta
        // Indicamos al navegador que la respuesta es HTML y usa codificación UTF-8.
        resp.setContentType("text/html;charset=UTF-8");

        // Paso 2: Establecer la cabecera "refresh"
        // Esta cabecera le dice al navegador que recargue la página cada 1 segundo.
        resp.setHeader("refresh","1");
        
        // Paso 3: Obtener la hora actual y formatearla       
        LocalTime hora = LocalTime.now();

        // Define un formateador para mostrar la hora en formato "HH:MM:SS".
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");

        // Paso 4: Escribir la respuesta HTML
        // Usamos try-with-resources para asegurar que el PrintWriter se cierre automáticamente.
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>La hora actualizada</title>");
            out.println("     </head>");
            out.println("     <body>");
            out.println("         <h1>La hora actualizada!</h1>");
            // Se inserta la hora formateada en una etiqueta <h3>.
            out.println("<h3>"+hora.format(df)+"</h3>");
            out.println("     </body>");
            out.println("</html>");
        }
    }
}
