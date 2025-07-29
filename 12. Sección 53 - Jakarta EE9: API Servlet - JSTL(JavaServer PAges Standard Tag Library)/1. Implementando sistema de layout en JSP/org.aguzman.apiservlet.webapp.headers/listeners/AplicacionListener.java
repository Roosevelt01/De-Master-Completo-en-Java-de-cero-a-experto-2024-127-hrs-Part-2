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
        servletContext.setAttribute("mensaje","algún valor globlal de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruyendo la aplicación!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando la aplicación!");
        sre.getServletRequest().setAttribute("mensaje","guardando algún valor para el request");
        // Paso 1: Establecer un título por defecto para cada request
        sre.getServletRequest().setAttribute("title","Catalogo Servlet");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo la aplicación!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("inicializando la session http!");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo la session http!");
    }
}
