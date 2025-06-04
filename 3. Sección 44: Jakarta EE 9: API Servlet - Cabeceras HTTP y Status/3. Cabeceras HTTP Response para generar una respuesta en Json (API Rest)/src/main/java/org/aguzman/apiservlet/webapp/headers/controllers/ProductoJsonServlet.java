package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@WebServlet("/productos.json")
 public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Obtener la lista de productos
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        // 2. Inicializar el serializador de JSON
        ObjectMapper mapper = new ObjectMapper();

        // 3. Serializar los productos a una cadena JSON
        String json = mapper.writeValueAsString(productos);

        // 4. Configurar la respuesta HTTP
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8"); // Asegura la codificación correcta para caracteres especiales

        // 5. Enviar la respuesta JSON al cliente
        resp.getWriter().write(json);

    }
}
