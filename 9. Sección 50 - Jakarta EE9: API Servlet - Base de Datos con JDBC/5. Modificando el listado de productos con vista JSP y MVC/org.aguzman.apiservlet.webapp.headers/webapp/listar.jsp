<%@page contentType="text/html;charset=UTF-8" import="java.util.*, org.aguzman.apiservlet.webapp.headers.models.*"%>

<%
    // Recuperamos el atributo "productos" y lo convertimos a una List<Producto>
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");

    // Hacemos lo mismo para la información del usuario
    Optional<String> usernameOptional = (Optional<String>) request.getAttribute("username");

    // Obtener mensaje de diferentes ámbitos.        
    String mensajeRequest = (String) request.getAttribute("mensaje");
    String mensajeApp = (String) getServletContext().getAttribute("mensaje");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <%-- Si el Optional<String> contiene un nombre, el usuario ha iniciado sesión --%>
    <%if(usernameOptional.isPresent()){%>
        <div style='color:blue;'>¡Hola <%=usernameOptional.get()%>, bienvenido!</div>
    <% } %>

    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <%-- Solo mostramos estas cabeceras si el usuario está logueado --%>
            <%if(usernameOptional.isPresent()){%>
                <th>precio</th>
                <th>agregar</th>
            <% } %>
        </tr>

        <%for(Producto p: productos){%>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getTipo()%></td>
                <%-- Solo mostramos estas cabeceras si el usuario está logueado --%>
                <%if(usernameOptional.isPresent()){%>
                    <td><%=p.getPrecio()%></td>
                    <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">agregar al carro</a></td>
                <%}%>
            </tr>
        <%}%>
    </table>
    <p><%=mensajeApp%></p>
    <p><%=mensajeRequest%></p>
</body>
</html>