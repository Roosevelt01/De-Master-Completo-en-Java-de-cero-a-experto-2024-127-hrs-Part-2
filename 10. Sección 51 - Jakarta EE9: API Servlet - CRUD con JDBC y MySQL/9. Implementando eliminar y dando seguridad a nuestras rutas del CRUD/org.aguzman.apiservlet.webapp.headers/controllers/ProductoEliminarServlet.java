package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // --- Paso 1: Obtener la Conexión y el ID ---
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        // --- Paso 2: Validar el ID ---
        if(id > 0){
            // --- Paso 3: Validar la Existencia del Producto ---
            Optional<Producto> o = service.porId(id);
            if(o.isPresent()){
                // --- Paso 4: Ejecutar la Eliminación y Redirigir ---
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "¡No existe el producto en la base de datos!");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "¡Error el id es Null, se debe enviar como parámetro en la url!");
        }
    }
}
