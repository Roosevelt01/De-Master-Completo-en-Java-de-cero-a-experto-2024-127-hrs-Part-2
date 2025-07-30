package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Usuario;

import java.util.Optional;

public interface UsuarioService {
        /**
     * Intenta autenticar a un usuario con el nombre de usuario y contraseña proporcionados.
     * username El nombre de usuario.
     * password La contraseña.
     * @return Un Optional que contiene el objeto Usuario si la autenticación es exitosa,
     * o un Optional vacío si las credenciales no coinciden o el usuario no existe.
     */
    Optional<Usuario> login(String username, String password);
}
