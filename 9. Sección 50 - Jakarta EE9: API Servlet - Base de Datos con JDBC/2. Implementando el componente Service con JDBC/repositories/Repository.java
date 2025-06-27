

package org.aguzman.apiservlet.webapp.headers.repositories;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para los Repositorios de datos.
 * Define las operaciones CRUD (Create, Read, Update, Delete) estándar.
 */
public interface Repository<T> {
    // Devuelve una lista de todos los objetos de tipo T.
    List<T> listar() throws SQLException;

    // Busca y devuelve un objeto de tipo T por su ID.
    T porId(Long id) throws SQLException;

    // Guarda un objeto de tipo T (ya sea para crear uno nuevo o actualizar uno existente).
    void guardar(T t) throws SQLException;

    // Elimina un objeto por su ID.
    void eliminar(Long id) throws SQLException;
}
