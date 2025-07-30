package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Usuario;

// Importa la clase SQLException
import java.sql.SQLException;

// Extiende la interfaz genérica Repository
public interface UsuarioRepository extends Repository<Usuario>{
    // Método para buscar un usuario por su nombre de usuario
    Usuario porUsername(String username) throws SQLException;
}
