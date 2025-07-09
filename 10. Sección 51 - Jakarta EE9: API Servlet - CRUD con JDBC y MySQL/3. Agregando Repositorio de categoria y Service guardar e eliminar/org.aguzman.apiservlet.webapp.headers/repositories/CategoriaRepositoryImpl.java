package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Se declara la clase pública 'CategoriaRepositoryImpl'
// y se especifica que implementa la interfaz genérica 'Repository' para el tipo 'Categoria'
public class CategoriaRepositoryImpl implements  Repository<Categoria>{
    // 1. Atributo privado para guardar la conexión
    private Connection conn;

    // 2. Constructor para recibir la conexión (Inyección de Dependencias)
    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        
        List<Categoria> categorias = new ArrayList<>();
        // 1. Se usa try-with-resources para asegurar que Statement y ResultSet se cierren solos
        try (Statement stmt =  conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from categorias")){

            // 2. Se itera sobre cada fila del resultado
            while(rs.next()){
                // 3. Se delega la creación del objeto a un método auxiliar
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        // 1. Se usa PreparedStatement porque la consulta tiene un parámetro (?)
        try (PreparedStatement stmt = conn.prepareStatement("select * from categorias as c where c.id=?")){
            // 2. Se asigna el valor del ID al primer parámetro (?)
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                // 3. Se usa if (no while) porque esperamos un solo resultado
                if(rs.next()){
                    // 4. Se reutiliza el mismo método auxiliar de mapeo
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }
}
