package org.aguzman.hibernateapp.repositores;

import org.aguzman.hibernateapp.entity.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

// Implementación del CrudRepository para la entidad Cliente.
public class ClienteRepository implements CrudRepository<Cliente> {
    // Atributo para el EntityManager, que gestiona el acceso a la base de datos.
    private EntityManager em;

    // Constructor que recibe el EntityManager como dependencia.
    public ClienteRepository(EntityManager em) {
        this.em = em;
    }

    // Método para listar todos los clientes usando HQL (Hibernate Query Language).
    @Override
    public List<Cliente> listar() {
        return return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    // Método para buscar un cliente por su ID usando em.find().
    @Override
    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }

    // Método para guardar o actualizar un cliente.
    @Override
    public void guardar(Cliente cliente) {
        // Si el cliente tiene un ID válido (mayor que 0), se trata de una actualización.
        if(cliente.getId() != null && cliente.getId() > 0){
            em.persist(cliente);// Sincroniza el objeto 'cliente' con la base de datos.
        }else{
            // Si el cliente no tiene un ID, es un nuevo registro.
            em.persist(cliente); // Registra el nuevo objeto en el contexto de persistencia.
        }
    }

    // Método para eliminar un cliente por su ID.
    @Override
    public void eliminar(Long id) {
        // Se busca la entidad gestionada y se marca para su eliminación.
        Cliente cliente = porId(id);
        em.remove(cliente);
    }
}
