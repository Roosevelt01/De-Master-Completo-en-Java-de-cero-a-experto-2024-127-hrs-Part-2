package org.aguzman.apiservlet.webapp.headers.models.Entities;


import jakarta.persistence.*;

@Entity//Paso 1
@Table(name = "categorias")//Paso 2
public class Usuario {
    @Id//Paso 3
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Paso 4
    private Long id;
    private String username;
    private String password;
    private String email;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
