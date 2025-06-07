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
import java.util.Optional;

 // Mapea este Servlet a la URL "/buscar-producto"
@WebServlet("/buscar-producto")
public class BuscarProductosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se asume que `ProductoServiceImpl` proporciona métodos para acceder a una lista de productos.
        // En una aplicación real, esto interactuaría con una base de datos o un repositorio.        
        ProductoService service = new ProductoServiceImpl();

        // El valor de `name="producto"` del input HTML se recupera aquí.
        String nombre = req.getParameter("producto");

        // `service.listar()`: Obtiene una lista de todos los productos disponibles.
        // `.stream()`: Convierte la lista en un Stream, permitiendo operaciones funcionales.
        // `.filter(p -> { ... })`: Filtra los productos basados en el nombre buscado.
        Optional<Producto> encontrado = service.listar().stream().filter(p -> {
            // Validación dentro del filtro:
            // Si el nombre buscado es nulo o está en blanco, significa 
            // que el usuario no ingresó nada válido.
            
            // En este caso, no hay coincidencia, por lo que devolvemos `false`
            //  para que el producto no sea incluido en el resultado.
            if(nombre == null || nombre.isBlank()){
                return false;
            }
            // Compara si el nombre del producto (convertido a minúsculas para una
            // búsqueda insensible a mayúsculas/minúsculas)
            
            // contiene el texto buscado por el usuario (también en minúsculas).
            return p.getNombre().contains(nombre);
        }).findFirst();
        // `.findFirst()`: Devuelve un `Optional<Producto>` con el primer producto
        //  que coincida, o un `Optional` vacío si no se encuentra ninguno.

        // `encontrado.isPresent()`: Verifica si el Optional contiene 
        // un producto (es decir, si se encontró una coincidencia).
        if(encontrado.isPresent()){
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                // Genera el HTML de la página de éxito, mostrando el nombre y precio del producto encontrado.
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title></title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Producto encontrado!</h1>");
                out.println("          <h3>Producto encontrado "+encontrado.get().getNombre()+
                                        " el precio $" +encontrado.get().getPrecio());
                out.println("     </body>");
                out.println("</html>");
            }
        }else{
            // Si el producto NO fue encontrado:
            // Envía un error HTTP 404 Not Found al cliente.
            // `HttpServletResponse.SC_NOT_FOUND` es una constante para el código 404.
            // Podemos añadir un mensaje personalizado como segundo parámetro para el usuario.
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
