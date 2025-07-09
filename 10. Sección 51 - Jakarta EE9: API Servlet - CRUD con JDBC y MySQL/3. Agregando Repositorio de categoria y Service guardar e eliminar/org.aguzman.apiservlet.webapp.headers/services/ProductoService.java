package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(Long id);

    // Paso 1: Método para guardar (crear/actualizar) un producto
    void guardar(Producto producto);

    // Paso 2: Método para eliminar un producto
    void eliminar(Long id);

    // Paso 3: Método para listar todas las categorías
    List<Categoria> listarCategoria();

    // Paso 4: Método para buscar una categoría por su ID
    Optional<Categoria> porIdCategoria(Long id);
}
