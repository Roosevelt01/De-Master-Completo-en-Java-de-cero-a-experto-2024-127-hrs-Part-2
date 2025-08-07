package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.aguzman.apiservlet.webapp.headers.configs.ProductoServicePrincipal;
import org.aguzman.apiservlet.webapp.headers.configs.Service;
import org.aguzman.apiservlet.webapp.headers.interceptors.Logging;
import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;
import org.aguzman.apiservlet.webapp.headers.repositories.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


// Reemplazamos @Logging y @ApplicationScoped por nuestro nuevo estereotipo.
@Service
//@Logging
//@ApplicationScoped
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService{
    @Inject
    private CrudRepository<Producto> repositoryJdbc;

    @Inject
    private CrudRepository<Categoria> repositoryCategoriaJdbc;


    @Override
    //@Logging
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    //@Logging
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}

// Opción 1: Interceptar un Método Específico
@ApplicationScoped
@ProductoServicePrincipal
public class ProductoServiceJdbcImpl implements ProductoService {

    @Logging // Aplicamos el interceptor solo a este método.
    @Override
    public List<Producto> listar() {
        // ...
    }

    @Logging // Y a este otro.
    @Override
    public Optional<Producto> porId(Long id) {
        // ...
    }
    // ... otros métodos no serán interceptados.
}


// Opción 2: Interceptar Todos los Métodos de una Clase
@Service
@Logging // Aplica el interceptor a TODOS los métodos de esta clase
@RequestScoped
public class ProductoServiceJdbcImpl implements ProductoService {
    // ...
    @Override
    public List<Producto> listar() {
        // ...
    }

    @Override
    public Optional<Producto> porId(Long id) {
        // ...
    }
    // ...
}
