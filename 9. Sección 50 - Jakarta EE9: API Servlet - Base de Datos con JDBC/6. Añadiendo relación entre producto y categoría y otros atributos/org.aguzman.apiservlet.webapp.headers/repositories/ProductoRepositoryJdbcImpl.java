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

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();

        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku")); //Paso 1
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());//Paso 2
        Categoria c = new Categoria();//Paso 3
        c.setId(rs.getLong("categoria_id")); //Paso 4
        c.setNombre(rs.getString("categoria")); //Paso 5
        p.setCategoria(c);//PAso 6
        return p;
    }
}
