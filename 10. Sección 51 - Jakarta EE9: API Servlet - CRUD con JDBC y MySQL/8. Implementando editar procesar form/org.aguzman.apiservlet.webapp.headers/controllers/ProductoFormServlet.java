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
import java.time.DateTimeException;
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
        long id;

        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch(NumberFormatException e){
            id = 0L;
        }

        Producto producto = new Producto();

        producto.setCategoria(new Categoria());

        if(id > 0){
            Optional<Producto> o = service.porId(id);
            if(o.isPresent()){
                producto = o.get();
            }
        }

        req.setAttribute("categorias", service.listarCategoria());//Paso 4(Gemini documento porque se mobio esa línea de código)
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

        //Paso 2: Capturar y Asignar el ID y fecha del Producto
        LocalDate fecha;
        long id;

        try{
            id= Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        try{
            fecha = LocalDate.parse(fechaSte, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeException e){
            fecha = null;
        }

        // Paso 1: Se crea el objeto Producto y se puebla con los datos del formulario        
        Producto producto = new Producto();
        producto.setId(id);//Paso 3: Se asigna el ID que se obtuvo del campo oculto del formulario.
        producto.setNombre(nombre);
        producto.setSku(sku);
        producto.setPrecio(precio);
        producto.setFechaRegistro(fecha);

        //Paso 2 Se obtiene el 'categoriaId' del formulario previamente.

        // Se crea una instancia vacía de Categoria.
        Categoria categoria = new Categoria();

        // Se establece únicamente el ID en el objeto Categoria.
        categoria.setId(categoriaId);

        // Se asigna el objeto Categoria (con su ID) al producto.
        producto.setCategoria(categoria);

        if(errores.isEmpty()) {

            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        }else{
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategoria());
            req.setAttribute("producto", producto);

            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}