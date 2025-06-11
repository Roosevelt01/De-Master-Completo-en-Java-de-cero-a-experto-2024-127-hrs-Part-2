package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

// Esta interfaz define el contrato que deben seguir las implementaciones del servicio de login.
public interface LoginService {

    // Declara el método getUsername que debe ser implementado
    Optional<String> getUsername(HttpServletRequest request);
}

