package org.aguzman.hibernateapp.services;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.repositores.ClienteRepository;
import org.aguzman.hibernateapp.repositores.CrudRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {
    private EntityManager em;
    private CrudRepository<Cliente> repository;

    // El constructor recibe el EntityManager como dependencia.
    // Con él, inicializamos una instancia de nuestro ClienteRepository.
    public ClienteServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new ClienteRepository(em);
    }

    // El método listar solo delega la llamada al repositorio.
    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }

    // El método porId delega al repositorio y envuelve el resultado en un Optional.
    @Override
    public Optional<Cliente> porId(Long id) {
        // Optional.ofNullable maneja el caso de que el resultado sea nulo.
        return Optional.ofNullable(repository.porId(id));
    }

    // El método guardar maneja la transacción.
    @Override
    public void guardar(Cliente cliente) {
        try{
            em.getTransaction().begin();
            repository.guardar(cliente);// La lógica de guardar/actualizar está en el repositorio.
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    // El método eliminar también maneja la transacción.
    @Override
    public void eliminar(Long id) {
        try{
            em.getTransaction().begin();
            repository.eliminar(id); // La lógica de eliminación está en el repositorio.
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}