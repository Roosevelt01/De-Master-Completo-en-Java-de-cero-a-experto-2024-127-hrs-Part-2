package org.aguzman.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.configs.MysqlConn;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;
import org.aguzman.apiservlet.webapp.headers.util.ConexionBaseDatosDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    //Paso 1: Gemini explicame por que se comenta
    /*@Inject
    @MysqlConn
    private Connection conn;*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Paso 2: Gemini explicame por que se comenta
        /*try{
            Connection connRequest = this.conn;//Me explicar porque ya no se usar dentro del paréntisis del try
            if(connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }*/

            try{
                chain.doFilter(request,response);
                //Paso 3: Gemini explicame por que se comenta
                //connRequest.commit();
            }catch (ServiceJdbcException e){
                //Paso 4: Gemini explicame por que se comenta
                //connRequest.rollback();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        //Paso 3: Gemini explicame por que se comenta
        /*} catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}
