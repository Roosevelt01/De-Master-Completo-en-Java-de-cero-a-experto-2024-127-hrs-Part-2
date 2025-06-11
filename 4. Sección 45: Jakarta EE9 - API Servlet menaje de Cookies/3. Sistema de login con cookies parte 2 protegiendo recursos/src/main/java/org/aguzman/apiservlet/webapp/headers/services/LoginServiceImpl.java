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
        Cookie[] cookies = req.getCookies() != null? req.getCookies():new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
