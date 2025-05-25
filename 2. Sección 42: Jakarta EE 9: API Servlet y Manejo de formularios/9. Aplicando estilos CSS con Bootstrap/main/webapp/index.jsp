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
    <!-- Bootstrap CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

    <!-- Bootstrap JS Bundle con Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

    
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
                <div class="col-sm-4"><input type="text" name="username" id="username" class="form-control"></div>
                  
                <%
                    if(errores != null && errores.containsKey("username")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("username") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="password" class="col-form-label col-sm-2">Password</label>
                <div class="col-sm-4"><input type="password" name="password" id="password" class="form-control"></div>

                <%
                    if(errores != null && errores.containsKey("password")){
                        out.println("<small class='alert alert-danger col-sm-4' style='color:red';>"+ errores.get("password") + "</small>");
                    }
                %>
            </div>

            <div class="row mb-3">
                <label for="email" class="col-form-label col-sm-2">Email</label>
                <div class="col-sm-4"><input type="text" name="email" id="email" class="form-control"></div>

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
                        <option value="ES">España</option>
                        <option value="MX">México</option>
                        <option value="CL">Chile</option>
                        <option value="AE">Argentina</option>
                        <option value="PE">Perú</option>
                        <option value="CO">Colombia</option>
                        <option value="VE">Venezuela</option>
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
                        <option value="Javase">Java SE</option>
                        <option value="javaee">Jakarta EE9</option>
                        <option value="spring">Spring Boot</option>
                        <option value="js">JavaScript</option>
                        <option value="angular">Angular</option>
                        <option value="react">React</option>
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
                
                <div class="form-check">
                    <input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input">
                    <label class="form-check-label">Administrador</label>
                </div>

                <div class="form-check">
                    <input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input">
                    <label class="form-check-label">Usuario</label>
                </div>

                <div class="form-check">
                    <input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input">
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
                <div class="form-check">
                    <input type="radio" name="idioma" value="es" class="form-check-input">
                    <label class="form-check-label">Español</label>
                </div>

                <div class="form-check">
                    <input type="radio" name="idioma" value="en" class="form-check-input">
                    <label class="form-check-label">Inglés</label>
                </div>

                <div class="form-check">
                    <input type="radio" name="idioma" value="fr" class="form-check-input">
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
                <div class="form-check">
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