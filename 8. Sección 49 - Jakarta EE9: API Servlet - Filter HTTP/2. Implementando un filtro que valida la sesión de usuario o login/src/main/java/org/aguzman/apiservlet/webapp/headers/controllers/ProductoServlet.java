package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        //Paso 4
        String mensajeRequest = (String) req.getAttribute("mensaje");
        String mensajeApp = (String) getServletContext().getAttribute("mensaje");

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> sessionOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Listado de Productos</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Listado de Productos</h1>");

                if(sessionOptional.isPresent()){
                    out.println("<div style='color:blue;'>Hola " +sessionOptional.get()+ " Bienvenido!</div>");
                }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");

            if(sessionOptional.isPresent()){
                out.println("<th>precio</th>");
                out.println("<th>agregar</th>");
            }
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    if(sessionOptional.isPresent()){
                        out.println("<td>" + p.getPrecio() + "</td>");
                        out.println("<td><a href=\"" + req.getContextPath() + "/agregar-carro?id=" + p.getId() + "\">Agregar al carro</td>");
                    }
                out.println("</tr>");
            });
            out.println("</table>");
                //Paso 5
                out.println("<p>"+mensajeApp+"<p>");
                out.println("<p>"+mensajeRequest+"<p>");
                out.println("</body>");
                out.println("</html>");
        }
    }
}
