package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabeceraHttpRequestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
   String url2 = scheme + "://" + ip + ":" + port + contextPath + servletPath;


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
