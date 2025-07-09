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

        if(producto.getId() != null && producto.getId() > 0){
            sql = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
        }else{
            sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
        }

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());

            if(producto.getId() != null && producto.getId() > 0){
                stmt.setLong(5, producto.getId());
            }else{
                stmt.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from productos where id=?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();

        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        //Paso 1: Mapear el nuevo campo 'sku'
        p.setSku(rs.getString("sku"));
        //Paso 2: Mapear la fecha y convertirla al tipo correcto
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        //Paso 3: Crear una instancia para la categoría relacionada
        Categoria c = new Categoria();
        //Paso 4: Poblar el nombre de la categoría desde la llave foránea
        c.setId(rs.getLong("categoria_id"));
        //Paso 5: Poblar el nombre de la categoría desde la columna del JOIN
        c.setNombre(rs.getString("categoria"));
        //Paso 6: Asignar el objeto Categoria completo al Producto
        p.setCategoria(c);
        return p;
    }
}
