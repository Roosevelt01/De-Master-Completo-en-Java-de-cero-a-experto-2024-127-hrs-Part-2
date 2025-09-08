package org.aguzman.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.aguzman.apiservlet.webapp.headers.configs.Repository;
import org.aguzman.apiservlet.webapp.headers.models.Entities.Producto;

import java.util.List;

// Paso 1: Marca esta clase como un componente gestionado por el contenedor de Jakarta EE.
@Repository
public class ProductoRepositoryJpaImpl implements CrudRepository<Producto>{

    // Paso 2: Inyecta una instancia del EntityManager, el cual es el objeto central de JPA para interactuar con la base de datos.
    @Inject
    private EntityManager em;// Inyecta el EntityManager producido por ProducerResources

    @Override
    public List<Producto> listar() throws Exception {
        // Paso 3: Ejecuta una consulta JPQL simple para obtener todos los productos.
        return em.createQuery("from Producto", Producto.class).getResultList();
    }

    @Override
    public Producto porId(Long id) throws Exception {
        // Paso 4: Busca una entidad por su ID de forma directa y eficiente.
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
         // Paso 5: Decide entre actualizar o insertar una entidad.
        if(producto.getId() != null && producto.getId() > 0){
            em.merge(producto); // Operación de actualización (UPDATE) si el ID ya existe.
        }else{
            em.persist(producto); // Operación de inserción (INSERT) si el ID es nulo.
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        // Paso 6: Elimina una entidad que debe estar en el contexto de persistencia.
        Producto producto = porId(id);
        em.remove(producto);
    }
}
