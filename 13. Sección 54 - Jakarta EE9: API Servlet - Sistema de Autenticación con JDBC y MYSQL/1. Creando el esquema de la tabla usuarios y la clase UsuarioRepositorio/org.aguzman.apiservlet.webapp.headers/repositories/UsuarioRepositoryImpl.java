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
        Usuario usuario = null;

        try(PreparedStatement stmt = conn.prepareStatement("select * from usuarios where username=?")){
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        usuario = new Usuario();
                        usuario.setId(rs.getLong("id"));
                        usuario.setUsername(rs.getString("username"));
                        usuario.setPassword(rs.getString("password"));
                        usuario.setEmail(rs.getString("email"));
                    }
            }
        }
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
