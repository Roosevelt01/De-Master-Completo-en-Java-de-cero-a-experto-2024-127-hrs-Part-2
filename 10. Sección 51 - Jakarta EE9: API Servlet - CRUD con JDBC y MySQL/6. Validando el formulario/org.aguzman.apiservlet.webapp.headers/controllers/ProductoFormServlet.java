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

        Map<String, String> errores = new HashMap<>();//Paso 1

        //Paso 2
        if(nombre == null || nombre.isBlank()){
            errores.put("nombre", "El nombre es requerido!");
        }

        //Paso 3
        if(sku == null || sku.isBlank()){
            errores.put("sku", "El sku es requerido!");
        }

        //Paso 4
        if(fechaSte == null || fechaSte.isBlank()){
            errores.put("fecha_registro", "La fecha es requerida!");
        }

        //Paso 5
        if(precio.equals(0)){
            errores.put("precio", "El precio es requerida!");
        }

        //Paso 7
        if(categoriaId.equals(0L)){
            errores.put("categoria", "La categoría es requerida!");
        }

        if(errores.isEmpty()) {//Paso 8
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