<%@page contentType="text/html" pageEncoding="UTF-8" import="org.aguzman.apiservlet.webapp.headers.models.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
    <h1>Carro de Compras</h1>
   <%
      Carro carro = (Carro) session.getAttribute("carro");
   %>

    <% if(carro == null || carro.getItems().isEmpty()){%>
        <p>Lo sentimos no hay productos en el carro </p>
    <%} else {%>
        <table>
            <tr>
                <th>id</th>
                <th>Nombre</th>
                <th>precio</th>
                <th>cantidad</th>
                <th>total</th>
            </tr>
            <%for(ItemCarro item: carro.getItems()){%>
            <tr>
                <td><%= item.getProducto().getId()%></td>
                <td><%= item.getProducto().getNombre()%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getImporte()%></td>
            </tr>
            <%}%>
            <tr>
                <td colspan="4" style="text-align:right;">Total</td>
                <td><%=carro.getTotal()%></td>
            </tr>
            <p><a href="<%=request.getContextPath()%>/productos">seguir comprando</p>
            <p><a href="<%=request.getContextPath()%>/index.html">volver</p>
        </table>
    <%}%>
</html>