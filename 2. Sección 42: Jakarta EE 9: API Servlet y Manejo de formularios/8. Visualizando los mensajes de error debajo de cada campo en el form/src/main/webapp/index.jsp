<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Paso 3: Importación del Mapa --%>
<%@page import="java.util.Map"%> 

<%-- Paso 4: Recuperamos el mapa de errores del request, haciendo casting a Map<String, String> --%>
<%
    Map<String, String> errores = (Map<String, String>)request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
</head>
<body>
    <h3>Formulario de usuarios</h3>

        <%
            if(errores != null && errores.size() > 0){
        %>
        <ul style="color: red;"> <%-- Recomendado: añadir estilo para resaltar errores --%>
            <%
                for(String error : errores.values()){ Muestra todos los mensajes de error en la parte superior
            %>
                    <li><%= error %></li>
            <%
                } // Cierre del for
            %>


        </ul>
        <%
            }
        %>

    <form action="/webapp-form/registro" method="post">

        <div>
            <label for="username">Usuario</label>
            <div><input type="text" name="username" id="username"></div>
            <%-- Paso 5 --%>
            <%
                if(errores != null && errores.containsKey("username")){
                    out.println("<small style='color:red';>"+ errores.get("username") + "</small>");
                }
            %>
        </div>

        <div>
            <label for="password">Password</label>
            <div><input type="password" name="password" id="password"></div>
            <%-- Paso 6 --%>
            <%
                if(errores != null && errores.containsKey("passrord")){
                    out.println("<small style='color:red';>"+ errores.get("password") + "</small>");
                }
            %>
        </div>

        <div>
            <label for="email">Email</label>
            <div><input type="text" name="email" id="email"></div>
            <%-- Paso 7 --%>
            <%
                if(errores != null && errores.containsKey("email")){
                    out.println("<small style='color:red';>"+ errores.get("email") + "</small>");
                }
            %>
        </div>

        <div>
            <label for="pais">País</label>
            <div>
                <select name="pais" id="pais">
                    <option value="">-- seleccionar  --</option>
                    <option value="ES">España</option>
                    <option value="MX">México</option>
                    <option value="CL">Chile</option>
                    <option value="AE">Argentina</option>
                    <option value="PE">Perú</option>
                    <option value="CO">Colombia</option>
                    <option value="VE">Venezuela</option>
                </select>
            </div>
            <%-- Paso 8 --%>
            <%
                if(errores != null && errores.containsKey("pais")){
                    out.println("<small style='color:red';>"+ errores.get("pais") + "</small>");
                }
            %>
        </div>

        <div>
            <label for="lenguajes">Lenguajes de programación</label>
            <div>
                <select name="lenguajes" id="lenguajes" multiple>
                    <option value="Javase">Java SE</option>
                    <option value="javaee">Jakarta EE9</option>
                    <option value="spring">Spring Boot</option>
                    <option value="js">JavaScript</option>
                    <option value="angular">Angular</option>
                    <option value="react">React</option>
                </select>
            </div>
            <%-- Paso 9 --%>
            <%
                if(errores != null && errores.containsKey("lenguajes")){
                    out.println("<small style='color:red';>"+ errores.get("lenguajes") + "</small>");
                }
            %>
        </div>

        <div>
            <label>Roles</label>
            <div>
                <input type="checkbox" name="roles" value="ROLE_ADMIN">
                <label>Administrador</label>
            </div>

            <div>
                <input type="checkbox" name="roles" value="ROLE_USER">
                <label>Usuario</label>
            </div>

            <div>
                <input type="checkbox" name="roles" value="ROLE_MODERATOR">
                <label>Moderador</label>
            </div>
            <%-- Paso 10 --%>
            <%
                if(errores != null && errores.containsKey("roles")){
                    out.println("<small style='color:red';>"+ errores.get("roles") + "</small>");
                }
            %>

        </div>

        <div>
            <label>Idiomas</label>
            <div>
                <input type="radio" name="idioma" value="es">
                <label>Español</label>
            </div>

            <div>
                <input type="radio" name="idioma" value="en">
                <label>Inglés</label>
            </div>

            <div>
                <input type="radio" name="idioma" value="fr">
                <label>Francés</label>
            </div>
            <%-- Paso 11 --%>
            <%
                if(errores != null && errores.containsKey("idioma")){
                    out.println("<small style='color:red';>"+ errores.get("idioma") + "</small>");
                }
            %>
        </div>

        <div>
            <label for="habilitar">Habilitar</label>
            <div>
                <input type="checkbox" name="habilitar" id="habilitar">
            </div>
        </div>

        <div>
            <input type="submit" value="Enviar">
        </div>

        <input type="hidden" name="secreto" value="12345">

    </form>
</body>
</html>