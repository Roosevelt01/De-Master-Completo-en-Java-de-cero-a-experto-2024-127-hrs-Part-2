package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos.json")
 public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Paso 1: Obtener el flujo de entrada de la petición (donde viene el JSON)
        ServletInputStream jsonStream = req.getInputStream();

        // Paso 2: Crear una instancia de ObjectMapper de Jackson
        // ObjectMapper es la clase central de Jackson para realizar operaciones de serialización
        // (Java a JSON) y deserialización (JSON a Java).
        ObjectMapper mapper = new ObjectMapper();

        // Paso 3: Deserializar el JSON del flujo de entrada a un objeto 'Producto'
        // 'mapper.readValue()' lee el JSON del 'jsonStream' y lo mapea automáticamente
        // a un objeto de la clase 'Producto'. Jackson usa los setters de 'Producto'
        // para asignar los valores de las propiedades JSON.
        // Si el flujo está vacío o el JSON es inválido, Jackson lanzará un MismatchedInputException.
        Producto producto = mapper.readValue(jsonStream, Producto.class);

        // Paso 4: Configurar la cabecera de la respuesta HTTP a HTML
        // Indicamos al cliente que la respuesta será una página HTML codificada en UTF-8.
        resp.setContentType("text/html;charset=UTF-8");

        // Paso 5: Generar y enviar el contenido HTML de la respuesta
        // Utilizamos un PrintWriter para escribir el código HTML directamente al cliente.
        // El bloque try-with-resources asegura que el PrintWriter se cierre automáticamente.
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>Detalle de producto desde json</title>");
            out.println("     </head>");
            out.println("     <body>");
            out.println("         <h1>Detalle de producto desde json!</h1>");
            out.println("<ul>");
                // Paso 6: Insertar los datos del producto recibido en el HTML
                // Accedemos a las propiedades del objeto 'producto' deserializado
                // y las incrustamos en la lista HTML para mostrarlas al usuario.
                out.println("<li>ID : " +producto.getId()+ "</li>");
                out.println("<li>Nombre : " +producto.getNombre()+ "</li>");
                out.println("<li>Tipo : " +producto.getTipo()+ "</li>");
                out.println("<li>Precio: " +producto.getPrecio()+ "</li>");
            out.println("</ul>");
            out.println("     </body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);

    }
}
