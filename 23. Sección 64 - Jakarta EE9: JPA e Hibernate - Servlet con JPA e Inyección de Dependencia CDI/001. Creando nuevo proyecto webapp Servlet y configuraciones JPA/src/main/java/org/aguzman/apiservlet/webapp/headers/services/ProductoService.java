package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);//Paso 1

    void eliminar(Long id);//Paso 2

    List<Categoria> listarCategoria();//Paso 3

    Optional<Categoria> porIdCategoria(Long id);//Paso 4
}
