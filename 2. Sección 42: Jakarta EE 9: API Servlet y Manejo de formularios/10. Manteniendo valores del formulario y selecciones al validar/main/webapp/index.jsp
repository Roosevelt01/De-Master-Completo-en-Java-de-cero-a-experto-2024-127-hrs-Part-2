<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>

<%
    Map<String, String> errores = (Map<String, String>)request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</head>
<body>
    <h3>Formulario de usuarios</h3>

        <%
            if(errores != null && errores.size() > 0){
        %>
       <ul class="alert alert-danger m-5 p-4">
            <%
                for(String error : errores.values()){
            %>
                    <li><%= error %></li>
            <%
                } // Cierre del for
            %>
        </ul>
        <%
            }// Cierre del if
        %>
    <div class="px-5">
        <form action="/webapp-form/registro" method="post">

            <div class="row mb-3">
                <label for="username" class="col-form-label col-sm-2">Usuario</label>
                <div class="col-sm-4">
                    <%-- Paso 1: Se usa ${param.username} para mantener el valor del campo 'username' si se reenvía el formulario --%>
                    <input type="text" name="username" id="username" class="form-control" value="${param.username}">
                </div>

                <%
                    if(errores != null && errores.containsKey("username")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("username") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="password" class="col-form-label col-sm-2">Password</label>
                <div class="col-sm-4"><input type="password" name="password" id="password" class="form-control"></div>
                <%-- El campo password no se pre-llena por seguridad --%> 
                
                <%
                    if(errores != null && errores.containsKey("password")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("password") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="email" class="col-form-label col-sm-2">Email</label>
                <div class="col-sm-4">
                    <%-- Paso 3: Se usa ${param.email} para mantener el valor del campo 'email' si se reenvía el formulario --%>
                    <input type="text" name="email" id="email" class="form-control" value="${param.email}">
                </div>

                <%
                    if(errores != null && errores.containsKey("email")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("email") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="pais" class="col-form-label col-sm-2">País</label>
                <div class="col-sm-4">
                    <select name="pais" id="pais" class="form-select">
                        <option value="">-- seleccionar  --</option>
                        <%-- Paso 4: Se usa ${param.pais} para comparar y marcar la opción seleccionada del 'país' --%>
                        <option value="ES" ${param.pais.equals("ES")?"selected":""}>España</option>
                        <option value="MX" ${param.pais.equals("MX")?"selected":""}>México</option>
                        <option value="CL" ${param.pais.equals("CL")?"selected":""}>Chile</option>
                        <option value="AE" ${param.pais.equals("AE")?"selected":""}>Argentina</option>
                        <option value="PE" ${param.pais.equals("PE")?"selected":""}>Perú</option>
                        <option value="CO" ${param.pais.equals("CO")?"selected":""}>Colombia</option>
                        <option value="VE" ${param.pais.equals("VE")?"selected":""}>Venezuela</option>
                    </select>
                </div>

                <%
                    if(errores != null && errores.containsKey("pais")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("pais") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="lenguajes" class="col-form-label col-sm-2">Lenguajes de programación</label>
                <div class="col-sm-4">
                    <select name="lenguajes" id="lenguajes" multiple class="form-select">
                        <%-- Paso 5: Lógica para mantener la selección múltiple de lenguajes usando Arrays.asList().contains() y paramValues --%>
                        <option value="Javase" ${paramValues.lenguajes.stream().anyMatch(v->v.equals("javase")).get()?"selected":""}>Java SE</option>
                        <option value="javaee" ${paramValues.lenguajes.stream().anyMatch(v->v.equals("javaee")).get()?"selected":""}>Jakarta EE9</option>
                        <option value="spring" ${paramValues.lenguajes.stream().anyMatch(v->v.equals("spring")).get()?"selected":""}>Spring Boot</option>
                        <option value="js"${paramValues.lenguajes.stream().anyMatch(v->v.equals("js")).get()?"selected":""}>JavaScript</option>
                        <option value="angular" ${paramValues.lenguajes.stream().anyMatch(v->v.equals("angular")).get()?"selected":""}>Angular</option>
                        <option value="react" ${paramValues.lenguajes.stream().anyMatch(v->v.equals("react")).get()?"selected":""}>React</option>
                    </select>
                </div>

                <%
                    if(errores != null && errores.containsKey("lenguajes")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("lenguajes") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label class="col-form-label col-sm-2">Roles</label>
                <div class="form-check col-sm-2">
                    <%-- Paso 6: Lógica para mantener la selección de roles usando Arrays.asList().contains() y paramValues --%>
                    <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input"
                    ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_ADMIN")).get()?"checked":""}>
                    <label class="form-check-label">Administrador</label>
                </div>

                <div class="form-check col-sm-2">
                    <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input"
                    ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_USER")).get()?"checked":""}>
                    <label class="form-check-label">Usuario</label>
                </div>

                <div class="form-check col-sm-2">
                    <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input"
                    ${paramValues.roles.stream().anyMatch(v->v.equals("ROLE_MODERATOR")).get()?"checked":""}>
                    <label class="form-check-label">Moderador</label>
                </div>

                <%
                    if(errores != null && errores.containsKey("roles")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("roles") + "</small>");
                    }
                %>

            </div>

            <div class="row mb-3">
                <label class="col-form-label col-sm-2">Idiomas</label>
                <div class="form-check col-sm-2">
                    <%-- Paso 7: Se usa ${param.idioma} para mantener la selección del radio button 'idioma' --%>
                    <input type="radio" name="idioma" value="es" class="form-check-input" ${param.idioma.equals("es")?"checked":""}>
                    <label class="form-check-label">Español</label>
                </div>

                <div class="form-check col-sm-2">
                    <input type="radio" name="idioma" value="en" class="form-check-input" ${param.idioma.equals("en")?"checked":""}>
                    <label class="form-check-label">Inglés</label>
                </div>

                <div class="form-check col-sm-2">
                    <input type="radio" name="idioma" value="fr" class="form-check-input" ${param.idioma.equals("fr")?"checked":""}>
                    <label class="form-check-label">Francés</label>
                </div>

                <%
                    if(errores != null && errores.containsKey("idioma")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("idioma") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="habilitar" class="col-form-label col-sm-2">Habilitar</label>
                <div class="form-check col-sm-3">
                    <input type="checkbox" name="habilitar" id="habilitar" class="form-check-input">
                </div>
            </div>

            <div class="row mb-3">
                <div>
                    <input type="submit" value="Enviar" class="btn btn-primary">
                 </div>
            </div>

            <input type="hidden" name="secreto" value="12345">

        </form>
    </div>
</body>
</html>