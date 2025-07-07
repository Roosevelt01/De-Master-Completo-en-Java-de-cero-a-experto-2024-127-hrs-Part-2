package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements  Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     " inner join categorias as c ON (p.categoria_id = c.id)")){

            while (rs.next()){
                Producto p = getProducto(rs);

                productos.add(p);
            }
        }

        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p" +
                " inner join categorias as c ON(p.categoria_id = c.id) WHERE p.id = ?")){
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        // Paso 1: Decidir la sentencia SQL a utilizar
        // Si el ID del producto no es nulo y es mayor que 0, se asume que es una actualización.
        // De lo contrario, se asume que es una inserción de un nuevo producto.
        if(producto.getId() != null && producto.getId() > 0){
            // SQL para actualizar un producto existente
            sql = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
        }else{
            // SQL para insertar un producto nuevo
            // Es crucial que el orden de los placeholders (?) coincida con el orden en que se establecen los parámetros.
            // 'fecha_registro' se incluye aquí porque solo se establece en la creación inicial.
            sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
        }

        // Paso 2: Preparar la sentencia para evitar SQL Injection
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //Paso 3: Asignar los parámetros comunes a ambas sentencias
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());

            // Paso 4: Asignar el parámetro condicional (el 5to '?')
            if(producto.getId() != null && producto.getId() > 0){
                // Si es un UPDATE, el 5to parámetro es el ID para el WHERE
                stmt.setLong(5, producto.getId());
            }else{
                // Si es un INSERT, el 5to parámetro es la fecha de registro
                // Paso 5: Se convierte de java.time.LocalDate a java.sql.Date
                stmt.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }

            // Paso 6: Ejecutar la sentencia (INSERT o UPDATE)
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // Paso 1: Definir la sentencia SQL de eliminación
        String sql = "delete from productos where id=?";

        // Paso 2: Preparar la sentencia y asignar el parámetro
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            // Paso 3: Ejecutar la eliminación
            stmt.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();

        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}