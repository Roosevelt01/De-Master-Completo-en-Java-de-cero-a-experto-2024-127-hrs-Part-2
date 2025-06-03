package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls", "/productos.html"})
public class ProductoXLsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Obtener la lista de productos
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        // 2. Determinar si la petición es para Excel o HTML
        String servletPatch = req.getServletPath();
        boolean esXls = servletPatch.endsWith(".xls");

        // 3. Configurar cabeceras de respuesta según el formato
        if(esXls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }else{          
            resp.setContentType("text/html;charset=UTF-8");
        }

        // 4. Escribir la respuesta HTML o Excel (tabla HTML)
        try (PrintWriter out = resp.getWriter()) {
            // Generar cabecera HTML solo si no es Excel                
            if(!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Listado de Productos</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Listado de Productos</h1>");
                out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls" + "\">Exportar a xls</a></p>");
            }

            // Generar la tabla HTML (común para ambos formatos)
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            
            // Cerrar etiquetas HTML solo si no es Excel
            if(!esXls){
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}

