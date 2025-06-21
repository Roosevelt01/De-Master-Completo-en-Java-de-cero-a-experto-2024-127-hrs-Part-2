package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    // Paso 1: Nuevo método para buscar un producto por su ID
    // Devuelve un Optional<Producto> porque el producto podría no encontrarse.
    Optional<Producto> porId(Long id);
}
