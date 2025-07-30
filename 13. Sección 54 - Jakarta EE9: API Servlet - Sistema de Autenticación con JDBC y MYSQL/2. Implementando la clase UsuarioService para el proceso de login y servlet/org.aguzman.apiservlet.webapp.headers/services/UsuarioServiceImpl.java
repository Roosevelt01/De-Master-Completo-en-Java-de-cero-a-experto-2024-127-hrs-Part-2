package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Usuario;
import org.aguzman.apiservlet.webapp.headers.repositories.UsuarioRepository;
import org.aguzman.apiservlet.webapp.headers.repositories.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

// UsuarioServiceImpl implementa la interfaz UsuarioService
public class UsuarioServiceImpl implements UsuarioService {
    // Atributo para la inyección del repositorio
    private UsuarioRepository usuarioRepository;

    // Constructor que recibe un objeto Connection para inicializar el repositorio
    public UsuarioServiceImpl(Connection connection) {
        // Se crea una instancia de UsuarioRepositoryImpl, pasando la conexión.
        // Esto es una forma de inyección de dependencias.
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            // Paso 1: Buscar el usuario por nombre de usuario usando el repositorio.
            // usuarioRepository.porUsername(username) devuelve un Usuario o null.
            // Optional.ofNullable() convierte ese Usuario (o null) en un Optional.
            return Optional.ofNullable(usuarioRepository.porUsername(username))
             // Paso 2: Filtrar el Optional.
            // Si el Optional contiene un usuario (no es vacío), aplica el filtro.
            // El filtro compara la contraseña del usuario recuperado (u.getPassword())
            // con la contraseña proporcionada (password).
            // Si coinciden, el Optional mantiene al usuario; si no, el Optional se vuelve vacío.
            .filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            // Si ocurre una SQLException durante la operación de base de datos,
            // se lanza una excepción personalizada ServiceJdbcException.
            // Esto es importante para propagar errores de forma controlada y
            // poder gestionar transacciones y rollbacks en capas superiores (e.g., filtros).
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
