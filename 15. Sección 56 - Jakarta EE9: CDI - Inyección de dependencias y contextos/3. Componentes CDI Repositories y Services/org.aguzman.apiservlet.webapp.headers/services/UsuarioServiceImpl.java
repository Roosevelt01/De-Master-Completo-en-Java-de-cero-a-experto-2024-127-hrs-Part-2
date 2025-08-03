package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.aguzman.apiservlet.webapp.headers.models.Usuario;
import org.aguzman.apiservlet.webapp.headers.repositories.UsuarioRepository;

import java.sql.SQLException;
import java.util.Optional;

// Paso 1: Configurar como bean de aplicación
@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    // Paso 2: Inyectar el repositorio a través del constructor
    @Inject
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository =  usuarioRepository;
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
