<%@page contentType="text/html;charset=UTF-8"%>
<%-- Paso 1 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>

    <%-- Paso 2 --%>
    <c:if test="${username.isPresent()}">
        <div>Hola <c:out value="${username.get()}" />, bienvenido!</div>
        <p><a href="<c:out value="${pageContext.request.contextPath}" />/productos/form">Crear [+]</a></p>
    </c:if>

    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <%-- Paso 3 --%>
            <c:if test="${username.present}">
                <th>precio</th>
                <th>agregar</th>
                <th>editar</th>
                <th>eliminar</th>
            </c:if>
        </tr>

        <%-- Paso 4 --%>
        <c:forEach items="${productos}" var="p">
            <tr>
                <td><c:out value="${p.id}" /></td>
                <td><c:out value="${p.nombre}" /></td>
                <td><c:out value="${p.categoria.nombre}" /></td>
                <c:if test="${username.present}">
                    <td><c:out value="${p.precio}" /></td>
                    <td><a href="${pageContext.request.contextPath}/carro/agregar?id=<c:out value="${p.id}" />">agregar al carro</a></td>
                    <td><a href="${pageContext.request.contextPath}/productos/form?id=<c:out value="${p.id}" />">editar</a></td>
                    <td><a onclick="return confirm('¿Estás seguro que desea eliminar?');" href="${pageContext.request.contextPath}/productos/eliminar?id=<c:out value="${p.id}" />">eliminar</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <%-- Paso 4 --%>
    <p>${applicationScope.mensaje}</p>
    <p>${requestScope.mensaje}</p>
</body>
</html>