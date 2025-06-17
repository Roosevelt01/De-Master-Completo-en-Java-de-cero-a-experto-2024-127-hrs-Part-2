package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        // Obtener la sesión HTTP de la solicitud
        // `request.getSession()` obtiene la sesión actual del cliente.
        // Si no existe una sesión (primer acceso), se crea una nueva.
        HttpSession session = request.getSession();
        
        // Intentar obtener el atributo "username" de la sesión
        // `session.getAttribute("username")` recupera el objeto asociado con la clave "username".
        // Se necesita un casting `(String)` porque `getAttribute` devuelve un `Object`.
        String username = (String) session.getAttribute("username");

        // Verificar si el nombre de usuario existe en la sesión
        // Si `username` no es nulo (es decir, el atributo "username" se encontró en la sesión),
        // se devuelve un `Optional` que contiene ese nombre de usuario.
        if(username != null){
            return Optional.of(username);
        }

        // Si el nombre de usuario no se encontró en la sesión (es nulo),
        // se devuelve un `Optional` vacío para indicar su ausencia.
        return Optional.empty();
    }
}
