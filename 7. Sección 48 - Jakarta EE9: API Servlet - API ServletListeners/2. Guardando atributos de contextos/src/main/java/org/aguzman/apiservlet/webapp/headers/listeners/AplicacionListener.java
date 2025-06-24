package org.aguzman.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.aguzman.apiservlet.webapp.headers.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicializando la aplicación!");
        servletContext = sce.getServletContext();
        // PASO 1: Se establece un atributo en el contexto de la aplicación.
        servletContext.setAttribute("mensaje","algún valor globl de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruyendo la aplicación!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando la aplicación!");
        // PASO 2: Se establece un atributo en el contexto de la petición.
        sre.getServletRequest().setAttribute("mensaje","guardando algún valor para el request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo la aplicación!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("inicializando la session http!");
        // Paso 3: Crear el objeto Carro y guardarlo en la sesión
        // Esto asegura que cada nueva sesión de usuario tenga un carrito inicializado
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo la session http!");
    }


}
