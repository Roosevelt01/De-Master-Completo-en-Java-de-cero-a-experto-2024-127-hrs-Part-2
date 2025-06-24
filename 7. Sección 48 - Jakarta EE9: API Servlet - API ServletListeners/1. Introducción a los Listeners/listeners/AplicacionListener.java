package org.aguzman.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    // Declaramos un atributo para guardar el contexto de la aplicación
    private ServletContext servletContext;

    // --- Métodos de ServletContextListener (Ámbito de Aplicación) ---
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Se invoca UNA SOLA VEZ, cuando la aplicación arranca.
        sce.getServletContext().log("inicializando la aplicación!");
        servletContext = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Se invoca UNA SOLA VEZ, cuando la aplicación se detiene.
        servletContext.log("destruyendo la aplicación!");
    }

    // --- Métodos de ServletRequestListener (Ámbito de Petición) ---
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // Se invoca por CADA petición HTTP que llega.
        servletContext.log("inicializando la aplicación!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // Se invoca por CADA petición HTTP, después de ser procesada.
        servletContext.log("destruyendo la aplicación!");
    }

    // --- Métodos de HttpSessionListener (Ámbito de Sesión) ---
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Se invoca cuando se crea una nueva sesión para un usuario.
        servletContext.log("inicializando la aplicación!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Se invoca cuando una sesión es invalidada (logout) o expira.
        servletContext.log("destruyendo la aplicación!");
    }
}
