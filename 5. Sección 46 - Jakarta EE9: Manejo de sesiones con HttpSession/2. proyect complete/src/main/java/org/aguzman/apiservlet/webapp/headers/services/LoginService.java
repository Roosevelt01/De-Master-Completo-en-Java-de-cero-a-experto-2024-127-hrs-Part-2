package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

//Paso 1
public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);
}
