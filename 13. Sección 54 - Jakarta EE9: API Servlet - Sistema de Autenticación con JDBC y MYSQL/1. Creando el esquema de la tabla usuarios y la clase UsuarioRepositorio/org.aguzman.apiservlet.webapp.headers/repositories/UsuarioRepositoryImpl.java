package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Importa la clase SQLException
public class UsuarioRepositoryImpl implements UsuarioRepository{
    // Inicializa el objeto Usuario como nulo
    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        // Inicializa el objeto Usuario como nulo
        Usuario usuario = null;

        // Define la sentencia SQL para buscar un usuario por su nombre de usuario
        try(PreparedStatement stmt = conn.prepareStatement("select * from usuarios where username=?")){
            // Establece el primer parámetro de la consulta (el 'username')
            stmt.setString(1, username);

            // Usa otro try-with-resources para asegurar que ResultSet se cierre automáticamente
            try (ResultSet rs = stmt.executeQuery()){// Ejecuta la consulta y obtiene el ResultSet
                    if(rs.next()){// Si hay un resultado (es decir, se encontró un usuario)

                        usuario = new Usuario();// Crea una nueva instancia de Usuario
                        // Asigna los valores de las columnas del ResultSet a las propiedades del objeto Usuario
                        usuario.setId(rs.getLong("id"));
                        usuario.setUsername(rs.getString("username"));
                        usuario.setPassword(rs.getString("password"));
                        usuario.setEmail(rs.getString("email"));
                    }
            }
        }
        // Devuelve el objeto Usuario (será nulo si no se encontró ningún usuario)
        return usuario;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return List.of();
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
