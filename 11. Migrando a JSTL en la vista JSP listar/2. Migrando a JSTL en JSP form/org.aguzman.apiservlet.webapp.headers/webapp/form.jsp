<%-- Paso 1: Se elimina la importación de java.util.* y models.* --%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>

<%-- Paso 2: Se añade la directiva para usar las etiquetas JSTL con el prefijo "c" --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario productos</title>
</head>
<body>
<h1>Formulario productos</h1>
<form action="${pageContext.request.contextPath}/productos/form" method="post">
        
    <div>
        <label for="nombre">Nombre</label>
        <div>
            <%-- Paso 2: Poblar Campos de Texto y Mostrar Errores --%>
            <input type="text"  name="nombre" id="nombre" value="${producto.nombre}">
        </div>
        <c:if test="${errores!= null && errores.containsKey('nombre')}">
              <div style="color:red;">${errores.nombre}</div>
        </c:if>
    </div>
    
    <%-- Paso 2: Poblar Campos Numéricos y Mostrar Errores --%>
    <div>
        <label for="precio">Precio</label>
        <div>
            <input type="number"  name="precio" id="precio" 
            value="${producto.precio > 0? producto.precio : "" }">
        </div>
        <c:if test="${errores!= null && not empty errores.precio}">
            <div style="color:red;">${errores.precio}</div>
        </c:if>
    </div>

    <%-- Paso 3: Patrón para SKU y Fecha --%>
    <div>
        <label for="sku">sku</label>
        <div>
            <%-- Paso 7 --%>
            <input type="text"  name="sku" id="sku" value="${producto.sku}">
        </div>

        <%-- Paso 8 --%>
        <c:if test="${errores != null && not empty errores.sku}">
              <%-- Paso 9 --%>
              <div style="color:red;">${errores.sku}</div>
        </c:if>
    </div>

    <div>
        <label for="fecha_registro">Fecha Registros</label>
        <div>
            <%-- Paso 10 --%>
            <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
        </div>
        <%-- Paso 11 --%>
        <c:if test="${errores != null && not empty errores.fecha_registro}">
               <%-- Paso 12 --%>
               <div style="color:red;">${errores.fecha_registro}</div>
        </c:if>
    </div>

    <div>
        <label for="categoria">Categoría</label>
        <div>
            <select name="categoria" id="categoria">
                <option>---  Seleccionar ---</option>
                <%-- Paso 14 --%>
                <c:forEach items="${categorias}" var="c">
                    <%-- Paso 15 --%>
                    <option value="${c.id}" ${producto.categoria != null && c.id.equals(producto.categoria.id) ? 'selected' : ''}>${c.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <%-- Paso 16 --%>
        <c:if test="${errores != null && not empty errores.categoria}">
               <%-- Paso 17 --%>
               <div style="color:red;">${errores.categoria}</div>
        </c:if>
    </div>

    <%-- Paso 18 --%>
    <div><input type="submit" value="${producto.id!=null && producto.id>0? "Editar":"Crear"}"></div>

    <%-- Paso 19 --%>
    <input type="hidden" name="id" value="${producto.id}">

</form>
</body>
</html>