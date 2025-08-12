package org.aguzman.hibernateapp.repositores;

import java.util.List;

// Interfaz genérica para operaciones CRUD. 'T' representa el tipo de entidad.
public interface CrudRepository<T>{
    // Devuelve una lista de todas las entidades del tipo 'T'.
    List<T> listar();

    // Busca y devuelve una entidad por su ID.
    // 'id' es el identificador único de la entidad.
    T porId(Long id);

    // Guarda una entidad nueva o existente.
    // Utiliza 'persist' para nuevos registros y 'merge' para actualizaciones.
    void guardar(T t);

    // Elimina una entidad por su ID.
    void eliminar(Long id);
}