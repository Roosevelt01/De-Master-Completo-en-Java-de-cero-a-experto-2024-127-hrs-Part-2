package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.services.ProductoService;
import org.aguzman.apiservlet.webapp.headers.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        req.setAttribute("categorias", service.listarCategoria());
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        String nombre = req.getParameter("nombre");

        Integer precio;
        try{
            precio = Integer.valueOf(req.getParameter("precio"));
        }catch (NumberFormatException e){
            precio = 0;
        }

        String sku = req.getParameter("sku");
        String fechaSte = req.getParameter("fecha_registro");
        Long categoriaId;

        try{
            categoriaId = Long.parseLong(req.getParameter("categoria"));
        }catch(NumberFormatException e){
            categoriaId = 0L;
        }

        // Paso 1: Crear un Mapa para almacenar los errores
        Map<String, String> errores = new HashMap<>();

        // Paso 2: Validar cada campo
        if(nombre == null || nombre.isBlank()){
            errores.put("nombre", "El nombre es requerido!");
        }

        // Paso 3: Decidir el flujo basado en si hay errores
        if(sku == null || sku.isBlank()){
            errores.put("sku", "El sku es requerido!");
        }


        if(fechaSte == null || fechaSte.isBlank()){
            errores.put("fecha_registro", "La fecha es requerida!");
        }


        if(precio.equals(0)){
            errores.put("precio", "El precio es requerida!");
        }


        if(categoriaId.equals(0L)){
            errores.put("categoria", "La categoría es requerida!");
        }

        // ... (Validaciones para fecha, precio y categoría) ...

        // Paso 3: Decidir el flujo basado en si hay errores
        if(errores.isEmpty()) {
            // --- RUTA DE ÉXITO: No hay errores ---
            // ... (Crear el Producto, guardarlo y redirigir) ...
            LocalDate fecha = LocalDate.parse(fechaSte, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setSku(sku);
            producto.setPrecio(precio);
            producto.setFechaRegistro(fecha);

            Categoria categoria = new Categoria();
            categoria.setId(categoriaId);

            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        }else{
            // Paso 4: Pasar los errores a la vista
            req.setAttribute("errores", errores);
            // Paso 5: Reutilizar el doGet para volver a mostrar el formulario
            doGet(req, resp);
        }
    }

}