package org.aguzman.apiservlet.webapp.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;// Importar java.io.PrintWriter
import java.util.Enumeration;

@WebServlet("/cabeceras-request")// Anotación para mapear el Servlet a una URL
public class CabeceraHttpRequestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   // Configuración de la respuesta HTTP
   resp.setContentType("text/html;charset=UTF-8");
   
   String metodoHttp = req.getMethod();
   String requestUri = req.getRequestURI();
   String requestUrl = req.getRequestURL().toString();
   String contextPath = req.getContextPath();
   String servletPath = req.getServletPath();
   String ipCliente = req.getRemoteAddr();
   String ip = req.getLocalAddr();
   int port = req.getLocalPort();
   String scheme = req.getScheme();
   String host = req.getHeader("host");
   String url = scheme + "://" + host + contextPath + servletPath;
   String url2 = scheme + ";//" + ip + ":" + port + contextPath + servletPath;


      try (PrintWriter out = resp.getWriter()) {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("     <head>");
    out.println("         <meta charset=\"UTF-8\">");
    out.println("         <title>Cabeceras HTTP Request</title>");
    out.println("     </head>");
    out.println("     <body>");
    out.println("         <h1>Cabeceras HTTP Request</h1>");
    out.println("<ul>");
       out.println("<li>Método http: " + metodoHttp + "</li>");
       out.println("<li>Request uri: " + requestUri + "</li>");
       out.println("<li>Request url: " + requestUrl + "</li>");
       out.println("<li>Context path: " + contextPath + "</li>");
       out.println("<li>Servlet Path: " + servletPath + "</li>");
       out.println("<li>ip Local: " + ip + "</li>");
       out.println("<li>ip Cliente: " + ipCliente + "</li>");
       out.println("<li>Puerto Local: " + port + "</li>");
       out.println("<li>Scheme: " + scheme + "</li>");
       out.println("<li>Host: " + host + "</li>");
       out.println("<li>Url: " + url + "</li>");
       out.println("<li>Url2: " + url2 + "</li>");

          Enumeration<String> headerNames = req.getHeaderNames();
          while(headerNames.hasMoreElements()){
              String cabecera = headerNames.nextElement();
              out.println("<li>" +cabecera+ ": " + req.getHeader(cabecera) + "</li>");
          }

    out.println("</ul>");
    out.println("     </body>");
    out.println("</html>");
   }
  }
}

package org.aguzman.apiservlet.webapp.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter; // Importar java.io.PrintWriter

@WebServlet("/cabeceras-request") // Anotación para mapear el Servlet a una URL
public class CabeceraHttpRequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configuración de la respuesta HTTP
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod(); // Obtiene el método HTTP de la petición (GET, POST, etc.)
        String requestUri = req.getRequestURI(); // Obtiene la URI de la petición (ej. /webapp-headers/cabeceras-request)
        String requestUrl = req.getRequestURL().toString(); // Obtiene la URL completa de la petición (ej. http://localhost:8080/webapp-headers/cabeceras-request)
        String contextPath = req.getContextPath(); // Obtiene el camino del contexto de la aplicación (ej. /webapp-headers)
        String servletPath = req.getServletPath(); // Obtiene el camino del servlet dentro del contexto (ej. /cabeceras-request)
        String ipCliente = req.getRemoteAddr(); // Obtiene la dirección IP del cliente remoto
        String ip = req.getLocalAddr(); // Obtiene la dirección IP local del servidor donde se recibió la petición
        int port = req.getLocalPort(); // Obtiene el puerto local del servidor donde se recibió la petición
        String scheme = req.getScheme(); // Obtiene el esquema de la URL (http o https)
        String host = req.getHeader("host"); // Obtiene el valor de la cabecera HTTP "Host"
        String url = scheme + "://" + host + contextPath + servletPath; // Construye una URL completa usando el host
        String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath; // Construye una URL completa usando la IP y puerto locales



        // Obtención de un objeto PrintWriter para escribir la respuesta HTML
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("     <head>");
            out.println("         <meta charset=\"UTF-8\">");
            out.println("         <title>Cabeceras HTTP Request</title>");
            out.println("     </head>");
            out.println("     <body>");
            out.println("         <h1>Cabeceras HTTP Request</h1>");
            out.println("<ul>"); // Inicio de una lista no ordenada para los detalles
              out.println("<li>Método http: " + metodoHttp + "</li>"); // Imprime el método HTTP
              out.println("<li>Request uri: " + requestUri + "</li>"); // Imprime la URI de la petición
              out.println("<li>Request url: " + requestUrl + "</li>"); // Imprime la URL completa de la petición
              out.println("<li>Context path: " + contextPath + "</li>"); // Imprime el camino del contexto
              out.println("<li>Servlet Path: " + servletPath + "</li>"); // Imprime el camino del servlet
              out.println("<li>ip Local: " + ip + "</li>"); // Imprime la IP local del servidor
              out.println("<li>ip Cliente: " + ipCliente + "</li>"); // Imprime la IP del cliente
              out.println("<li>Puerto Local: " + port + "</li>"); // Imprime el puerto local del servidor
              out.println("<li>Scheme: " + scheme + "</li>"); // Imprime el esquema de la URL
              out.println("<li>Host: " + host + "</li>"); // Imprime el valor de la cabecera Host
              out.println("<li>Url: " + url + "</li>"); // Imprime la URL construida con el host
              out.println("<li>Url2: " + url2 + "</li>"); // Imprime la URL construida con la IP y puerto locales

                  Enumeration<String> headerNames = req.getHeaderNames(); // Obtiene una enumeración de todos los nombres de cabecera
                  while(headerNames.hasMoreElements()){ // Itera sobre los nombres de cabecera
                      String cabecera = headerNames.nextElement(); // Obtiene el siguiente nombre de cabecera
                      out.println("<li>" +cabecera+ ": " + req.getHeader(cabecera) + "</li>"); // Imprime el nombre y el valor de cada cabecera
                  }
            
            out.println("</ul>"); // Cierre de la lista no ordenada
            out.println("     </body>");
            out.println("</html>");
        }
    }
}

