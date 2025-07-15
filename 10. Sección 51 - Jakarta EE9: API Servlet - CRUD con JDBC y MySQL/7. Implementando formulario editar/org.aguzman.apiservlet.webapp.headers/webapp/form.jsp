<%@page contentType="text/html" pageEncoding="UTF-8"
import="java.util.*,java.time.format.*,org.aguzman.apiservlet.webapp.headers.models.*"%>
<%
List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");

Producto producto = (Producto) request.getAttribute("producto");

// 1. `producto`: El objeto Producto a mostrar. Puede ser nuevo/vacío o prellenado para edición,
//                o con datos ingresados por el usuario si hubo errores de validación.

// 2. Formatear la fecha de registro del producto para el input HTML de tipo 'date'.
//    Si la fecha del producto no es nula, se formatea a "yyyy-MM-dd".
//    Si es nula (para un nuevo producto o si el usuario no la ingresó), se usa una cadena vacía.
String fecha = producto.getFechaRegistro() != null?
producto.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario productos</title>
</head>
<body>
<h1>Formulario productos</h1>
<form action="<%=request.getContextPath()%>/productos/form" method="post">
    <div>
        <label for="nombre">Nombre</label>
        <div>
            <input type="text"  name="nombre" id="nombre" value="<%=producto.getNombre() != null? producto.getNombre(): ""%>">
        </div>
        <% if(errores != null && errores.containsKey("nombre")){%>
              <div style="color:red;"><%=errores.get("nombre")%></div>
        <% } %>

    </div>

    <div>
        <label for="precio">Precio</label>
        <div>
            <input type="number"  name="precio" id="precio" value="<%=producto.getPrecio() != 0? producto.getPrecio():""%>">
        </div>

        <% if(errores != null && errores.containsKey("precio")){%>
              <div style="color:red;"><%=errores.get("precio")%></div>
        <% } %>
    </div>

    <div>
        <label for="sku">sku</label>
        <div>
            <input type="text"  name="sku" id="sku" value="<%=producto.getSku() != null? producto.getSku():"" %>">
        </div>

        <% if(errores != null && errores.containsKey("sku")){%>
              <div style="color:red;"><%=errores.get("sku")%></div>
        <% } %>
    </div>

    <div>
        <label for="fecha_registro">Fecha Registros</label>
        <div>
            <input type="date" name="fecha_registro" id="fecha_registro" value="<%=fecha%>">
        </div>

        <% if(errores != null && errores.containsKey("fecha_registro")){%>
              <div style="color:red;"><%=errores.get("fecha_registro")%></div>
        <% } %>
    </div>

    <div>
        <label for="categoria">Categoría</label>
        <div>
            <select name="categoria" id="categoria">
                <option>---  Seleccionar ---</option>
                <%for(Categoria c: categorias){%>
                    <option value="<%=c.getId()%>" <%=c.getId().equals(producto.getCategoria().getId())? "selected": ""%>><%=c.getNombre()%></option>
                <%}%>
            </select>
        </div>

        <% if(errores != null && errores.containsKey("categoria")){%>
              <div style="color:red;"><%=errores.get("categoria")%></div>
        <% } %>
    </div>

    <div><input type="submit" value="Crear"></div>

</form>
</body>
</html>