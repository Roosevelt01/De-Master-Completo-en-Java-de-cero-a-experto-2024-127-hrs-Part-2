<%-- Paso 1: Declarar la Librería de Etiquetas (taglib)
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
    <h1>Carro de Compras</h1>

    <%-- Paso 2: Reemplazar if-else con <c:choose> --%>
    <c:choose>
        <%-- Paso 3: Analizar la Condición en <c:when></c:when> --%>
        <c:when test="${carro == null || carro.items.isEmpty()}">
            <p>Lo sentimos no hay productos en el carro </p>
        </c:when>

        <%-- Paso 4: Implementar el Bloque <c:otherwise></c:otherwise> --%>
        <c:otherwise>
            <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
                <table>
                    <tr>
                        <th>id</th>
                        <th>Nombre</th>
                        <th>precio</th>
                        <th>cantidad</th>
                        <th>total</th>
                        <th>borrar</th>
                    </tr>

                    <%-- Paso 5: Iterar con <c:forEach> --%>
                    <c:forEach items="${carro.items}" var="item">
                    <tr>
                        <td>${item.producto.id}</td>
                        <td>${item.producto.nombre}</td>
                        <td>${item.producto.precio}</td>
                        <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}"></td>
                        <td>${item.importe}</td>
                        <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos" /></td>
                    </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="4" style="text-align:right;">Total</td>
                        <%-- Paso 11 --%>
                        <td>${carro.total}</td>
                    </tr>
                </table>
                <a href="javascript:document.formcarro.submit();">Actualizar</a>
            </form>
        </c:otherwise>
    </c:choose>

    <%-- Paso 6 --%>
    <p><a href="${pageContext.request.contextPath}/productos">seguir comprando</a></p>
    <%-- Paso 14 --%>
    <p><a href="${pageContext.request.contextPath}/index.html">volver</a></p>
</body>
</html>