package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
// Paso 1: Inicializar el EntityManager y el CriteriaBuilder.
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        // Paso 2: Crear el objeto CriteriaQuery y definir la entidad raíz (FROM).
        CriteriaQuery<Factura> query = cb.createQuery(Factura.class);
        Root<Factura> factura = query.from(Factura.class);
        
        // Paso 3: Realizar un JOIN FETCH a las colecciones del cliente.
        // Se utiliza 'fetch' para poblar las relaciones en el objeto de la entidad.
        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);

        // Paso 4: Construir la consulta completa con SELECT y DISTINCT.
        query.select(factura).where(cb.equal(cliente.get("id"), 1L));

        // Paso 5: Ejecutar la consulta y procesar los resultados.
        List<Factura> facturas = em.createQuery(query).getResultList();
        facturas.forEach(f-> System.out.println(f.getDescripcion() + " | cliente: "+ f.getCliente().getNombre()));

        // Paso 6: Cerrar el EntityManager.
        em.close();
    }
}
