<%@page contentType="text/html" pageEncoding="UTF-8"%> <%-- Paso 4 --%>
<%@page import="java.util.List"%> <%-- Paso 5 --%>

<%-- Paso 6 --%>
<%
    List<String> errores = (List<String>)request.getAttribute("errores");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
</head>
<body>
    <h3>Formulario de usuarios</h3>

        <%-- Paso 7 --%>
        <%
            if(errores != null && errores.size() > 0){
        %>
        <ul style="color: red;"> <%-- Recomendado: añadir estilo para resaltar errores --%>
            <%
                for(String error : errores){ // Bucle for-each para iterar sobre la lista de errores
            %>
                    <li><%= error %></li>
            <%
                }
            %>
        </ul>
        <%
            }
        %>

    <form action="/webapp-form/registro" method="post">

        <div>
            <label for="username">Usuario</label>
            <div><input type="text" name="username" id="username"></div>
        </div>

        <div>
            <label for="password">Password</label>
            <div><input type="password" name="password" id="password"></div>
        </div>

        <div>
            <label for="email">Email</label>
            <div><input type="text" name="email" id="email"></div>
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