package org.aguzman.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.aguzman.apiservlet.webapp.headers.configs.MysqlConn;
import org.aguzman.apiservlet.webapp.headers.configs.Repository;
import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//Paso 3: Renombramos el nombre de Repository a Repository con control shift + f6
@Repository
//Paso 4: Renombramos el nombre de Repository a CrudRepository con control shift + f6
public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {

    //Paso 5
     @Inject
     private Logger log;

    @Inject
    @MysqlConn
    private Connection conn;

    //Paso 1
    @PostConstruct
    public  void inicializar(){
        //System.out.println("Inicializando el beans " + this.getClass().getName());
        //Paso 6
        log.info("Inicializando el beans " + this.getClass().getName());

    }

    //Paso 2
    @PreDestroy
    public void destruir(){
        //System.out.println("Destruyendo el beans " + getClass().getName());
        // Paso 7
        log.info("Destruyendo el beans " + this.getClass().getName());
    }

    //Gemini después del Paso 2 se prueba la pero con el System.out.println, toma en cuenta eso para documentarlo
    //Gemini después del paso 7  se prueba la pero con el log.info, toma en cuenta eso para documentarlo

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     " inner join categorias as c ON (p.categoria_id = c.id) ORDER BY p.id ASC")){

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
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}


