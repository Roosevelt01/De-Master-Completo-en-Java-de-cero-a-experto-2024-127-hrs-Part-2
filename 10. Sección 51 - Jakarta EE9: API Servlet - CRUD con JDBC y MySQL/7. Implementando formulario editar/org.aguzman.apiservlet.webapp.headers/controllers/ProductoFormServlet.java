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

import javax.sound.sampled.Port;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        req.setAttribute("categorias", service.listarCategoria());
        Long id;// Paso 1: Declarar la variable para el ID

        // Paso 2: Capturar el ID del producto de la URL de forma segura
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch(NumberFormatException e){
            id = 0L;// Si no hay ID o no es un número, se asume 0 (modo Crear)
        }

        // Paso 3: Inicializar un objeto Producto
        Producto producto = new Producto();

        // Paso 4: Inicializar la categoría para evitar NullPointerException en la vista
        producto.setCategoria(new Categoria());

        // Paso 5: Si hay un ID válido, estamos en modo "Editar"
        if(id > 0){
            Optional<Producto> o = service.porId(id);
            if(o.isPresent()){
                producto = o.get();// Reemplazamos el producto vacío por el encontrado
            }
        }

        // Paso 6: Pasar el producto (vacío o poblado) a la vista
        req.setAttribute("producto", producto);

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

        Map<String, String> errores = new HashMap<>();

        if(nombre == null || nombre.isBlank()){
            errores.put("nombre", "El nombre es requerido!");
        }

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

        if(errores.isEmpty()) {
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
            req.setAttribute("errores", errores);
            doGet(req, resp);
        }
    }

}