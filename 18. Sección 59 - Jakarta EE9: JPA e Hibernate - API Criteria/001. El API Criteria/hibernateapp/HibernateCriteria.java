package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {

        // ======================= EJEMPLO 1: LISTAR TODOS LOS CLIENTES =======================

        // Paso 1: Obtiene el EntityManager, nuestro punto de entrada para las operaciones de persistencia.
        EntityManager em = JpaUtil.getEntityManager();

        // Paso 2: Obtiene el CriteriaBuilder, la fábrica para construir objetos de consulta.
        CriteriaBuilder criteria = em.getCriteriaBuilder();

        // Paso 3: Crea un objeto CriteriaQuery que definirá la estructura de nuestra consulta.
        // Se especifica que el tipo de resultado será Cliente.
        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);

        // Paso 4: Define la entidad raíz de la consulta (equivalente a "FROM Cliente").    
        Root<Cliente> from = query.from(Cliente.class);

        // Paso 5: Especifica qué se va a seleccionar (equivalente a "SELECT c FROM...").
        // En este caso, seleccionamos la entidad raíz completa.
        query.select(from);

        // Paso 6: Ejecuta la consulta Criteria que hemos construido y obtiene una lista de resultados.
        List<Cliente> clientes = em.createQuery(query).getResultList();

        // Paso 7: Imprimir la lista de clientes.
        clientes.forEach(System.out::println);

        // ======================= EJEMPLO 2: FILTRAR CON WHERE =======================

        System.out.println("\n========== Listar con WHERE equals (sin parámetros) ==========");
        
        // Paso 8: Se reinicia el objeto query para una nueva consulta.
        query = criteria.createQuery(Cliente.class);
        
        // Paso 9: Se define de nuevo el punto de partida (FROM).
        from = query.from(Cliente.class);
        
        // Paso 10: Se construye la consulta con un SELECT y un WHERE.
        // criteria.equal crea una condición de igualdad (nombre = "Andres").
        query.select(from).where(criteria.equal(from.get("nombre"), "Andres"));

        // Paso 11: Se ejecuta la consulta filtrada.
        clientes = em.createQuery(query).getResultList();

        // Paso 12: Se imprimen los resultados filtrados.
        clientes.forEach(System.out::println);

        // --- Alternativa con Parámetros (Recomendado) --

        System.out.println("\n========== Listar con WHERE equals (con parámetros) ==========");
        // Paso 13: Se reinicia el objeto query para una nueva consulta limpia.
        query = criteria.createQuery(Cliente.class);

        // Paso 14: Se define de nuevo el FROM.
        from = query.from(Cliente.class);
        
        // Paso 15: Se crea un objeto ParameterExpression que representa un parámetro nombrado (:nombre).
        // Se define su tipo (String) y su nombre ("nombre").
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        
        // Paso 16: Se construye la consulta, usando el parámetro en la cláusula WHERE.
        // La condición es "nombre = :nombre".
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));

        // Paso 17: Se ejecuta la consulta, y en este punto se asigna el valor real ("Andres")
        // al parámetro "nombre".
        clientes = em.createQuery(query).setParameter("nombre","John").getResultList();

        // Paso 18: Se imprime el resultado (en este caso, solo el cliente "Andres").
        clientes.forEach(System.out::println);

        // Paso 20: Cierra el EntityManager para liberar la conexión a la base de datos y otros recursos.
        // Este es un paso crucial para evitar fugas de recursos.
        em.close();
    }
}