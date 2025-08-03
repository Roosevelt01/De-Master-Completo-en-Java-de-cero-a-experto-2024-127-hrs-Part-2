package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

// Paso 1: Marcar como una implementación alternativa
@Alternative
public class LoginServiceCookieImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies() != null ? req.getCookies(): new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
