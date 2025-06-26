import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Un filtro simple que registra información sobre las peticiones entrantes.
 */
// La anotación @WebFilter registra el filtro y lo mapea a un patrón de URL.
// "/*" significa que interceptará TODAS las peticiones a la aplicación.
@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Este método se llama una vez cuando el contenedor de servlets inicia el filtro.
        // Se puede usar para inicializar recursos.
        System.out.println("Filtro de Logging [INICIADO]");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Casteamos el request a HttpServletRequest para acceder a información HTTP específica.
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 1. Lógica que se ejecuta ANTES de que la petición llegue al servlet.
        String remoteAddr = httpRequest.getRemoteAddr();
        String requestURL = httpRequest.getRequestURL().toString();
        System.out.println(new Date() + " - IP: " + remoteAddr + " - Petición a: " + requestURL);

        // 2. Pasar la petición al siguiente filtro en la cadena (o al servlet si es el último).
        // ESTA LÍNEA ES CRUCIAL. Si no se llama, la petición se detiene aquí.
        chain.doFilter(request, response);

        // 3. Lógica que se ejecuta DESPUÉS de que el servlet ha procesado la petición.
        // Por ejemplo, aquí se podría modificar la respuesta.
        // System.out.println("Procesamiento de la petición a " + requestURL + " finalizado.");
    }

    @Override
    public void destroy() {
        // Este método se llama cuando el contenedor de servlets va a destruir la instancia del filtro.
        // Se usa para liberar recursos.
        System.out.println("Filtro de Logging [DESTRUIDO]");
    }
}