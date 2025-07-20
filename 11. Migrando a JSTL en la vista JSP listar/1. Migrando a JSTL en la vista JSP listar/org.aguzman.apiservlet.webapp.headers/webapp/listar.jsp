<%@page contentType="text/html;charset=UTF-8"%>

<%-- Eliminado: <%@page import="java.util.*, org.aguzman.apiservlet.webapp.headers.models.*"%> --%>

<%-- Paso 1: Importar la librería JSTL Core con el prefijo "c" --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <%-- Paso 2: Reemplazar Scriptlet IF con <c:if> y Expression Language (EL) --%>
    <%-- Original: <%if(usernameOptional.isPresent()){%> --%>
    <c:if test="${username.isPresent()}">
        <div>Hola <c:out value="${username.get()}" />, bienvenido!</div>
        <p><a href="<c:out value="${pageContext.request.contextPath}" />/productos/form">Crear [+]</a></p>
    </c:if>

    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <%-- Paso 3: Reemplazar Scriptlet IF con <c:if> (para columnas condicionales) --%>
            <%-- Original: <%if(usernameOptional.isPresent()){%> --%>
            <c:if test="${username.present}">
                <th>precio</th>
                <th>agregar</th>
                <th>editar</th>
                <th>eliminar</th>
            </c:if>
        </tr>

        <%-- Paso 4: Reemplazar Scriptlet FOR con <c:forEach> --%>
        <%-- Original: <%for(Producto p: productos){%> --%>
        <c:forEach items="${productos}" var="p">
            <tr>
                <td><c:out value="${p.id}" /></td>
                <td><c:out value="${p.nombre}" /></td>
                <td><c:out value="${p.categoria.nombre}" /></td>

                <%-- Paso 5: Reemplazar Scriptlet IF con <c:if> (para datos condicionales de cada fila) --%>
                <%-- Original: <%if(usernameOptional.isPresent()){%> --%>
                <c:if test="${username.present}">
                    <td><c:out value="${p.precio}" /></td>
                    <td><a href="${pageContext.request.contextPath}/carro/agregar?id=<c:out value="${p.id}" />">agregar al carro</a></td>
                    <td><a href="${pageContext.request.contextPath}/productos/form?id=<c:out value="${p.id}" />">editar</a></td>
                    <td><a onclick="return confirm('¿Estás seguro que desea eliminar?');" href="${pageContext.request.contextPath}/productos/eliminar?id=<c:out value="${p.id}" />">eliminar</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    
    <%-- Paso 6: Acceder a atributos de ámbito con EL --%>
    <%-- Original: <p><%=mensajeApp%></p> --%>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
</body>
</html>
