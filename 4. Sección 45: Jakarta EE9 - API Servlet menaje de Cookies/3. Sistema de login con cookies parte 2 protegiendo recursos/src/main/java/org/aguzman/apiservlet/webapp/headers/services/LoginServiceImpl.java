package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

// Esta clase implementa la interfaz `LoginService`, lo que significa que debe proporcionar una implementación
// concreta para todos los métodos declarados en esa interfaz.
public class LoginServiceImpl implements LoginService {
    
    // declarado en la interfaz `LoginService`. Es una buena práctica para asegurar que la firma del
    // método coincida con la de la interfaz.
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        // Esto previene un `NullPointerException` en la siguiente línea cuando se intenta crear un stream a partir de `cookies`.
        Cookie[] cookies = req.getCookies() != null? req.getCookies():new Cookie[0];
        
        // Usa la API de Streams de Java 8 para buscar la cookie "username" de forma concisa y funcional.
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}

