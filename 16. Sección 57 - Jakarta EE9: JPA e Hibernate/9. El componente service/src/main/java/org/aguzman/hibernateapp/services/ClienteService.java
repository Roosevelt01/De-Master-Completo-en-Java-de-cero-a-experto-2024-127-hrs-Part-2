package org.aguzman.hibernateapp.services;

import org.aguzman.hibernateapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    // Devuelve una lista de todos los clientes.
    List<Cliente> listar();

    // Busca un cliente por su ID y lo devuelve en un objeto Optional.
    Optional<Cliente> porId(Long id);

    // Guarda o actualiza un cliente.
    void guardar(Cliente cliente);

    // Elimina un cliente por su ID.
    void eliminar(Long id);
}