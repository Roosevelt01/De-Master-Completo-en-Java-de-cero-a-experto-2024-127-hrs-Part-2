package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.LoginServiceImpl;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        // Paso 1: Instanciar el servicio de login para comprobar si hay una cookie de usuario.
        LoginServiceImpl auth = new LoginServiceImpl();// Crea una instancia de LoginServiceImpl.
        // Llama al método getUsername para verificar si el usuario ha iniciado sesión y obtener su nombre.
        Optional<String> cookieOptional = auth.getUsername(req);

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
                
                // Paso 2: Mostrar mensaje de bienvenida si el usuario está autenticado.
                // Verifica si el Optional contiene un nombre de usuario (es decir, si la cookie existe).
                if(cookieOptional.isPresent()){
                    // Imprime un mensaje de bienvenida con el nombre de usuario.
                    out.println("<div style='color:blue;'>Hola " +cookieOptional.get()+ " Bienvenido!</div>");
                }

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            // Paso 3: Mostrar la columna "precio" solo si el usuario está autenticado.
            if(cookieOptional.isPresent()){
                // Si la cookie de usuario está presente...
                // Imprime el encabezado de columna "precio".
                out.println("<th>precio</th>");
            }
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    // Paso 4: Mostrar el precio del producto solo si el usuario está autenticado.
                    if(cookieOptional.isPresent()){
                        // Si la cookie de usuario está presente...
                        // Imprime el precio del producto.
                        out.println("<td>" + p.getPrecio() + "</td>");
                    }
                out.println("</tr>");
            });
            out.println("</table>");
                out.println("</body>");
                out.println("</html>");
        }
    }
}
