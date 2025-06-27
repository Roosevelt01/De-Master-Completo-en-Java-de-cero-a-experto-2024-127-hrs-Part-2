package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        // Usamos try-with-resources para asegurar el cierre automático de Statement y ResultSet
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     " inner join categorias as c ON (p.categoria_id = c.id)")){

            // Iteramos sobre cada fila del resultado de la consulta
            while (rs.next()){
                // Usamos un método auxiliar para mapear la fila a un objeto Producto
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }

        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        //Asignar valores a los atributos del objeto Producto
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setTipo(rs.getString("categoria"));
        return p;
    }

    //Métodos porId, guardar y eliminar (implementación pendiente)
    @Override
    public Producto porId(Long id) throws SQLException {
        //implementación pendiente
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        //implementación pendiente
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        //implementación pendiente
    }
}
