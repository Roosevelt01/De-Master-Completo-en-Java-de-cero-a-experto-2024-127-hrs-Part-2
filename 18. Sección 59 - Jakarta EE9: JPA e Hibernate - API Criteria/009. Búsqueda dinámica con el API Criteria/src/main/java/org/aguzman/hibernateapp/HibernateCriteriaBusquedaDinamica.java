package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaBusquedaDinamica {
    public static void main(String[] args) {

        // --- 1. CAPTURA DE DATOS DEL USUARIO ---
        
        // Paso 1: Crea un objeto Scanner para leer la entrada desde la consola.
        Scanner s = new Scanner(System.in);

        // Paso 2: Pide al usuario que ingrese un filtro para el nombre.
        System.out.print("Filtro para el nombre: ");             
        // Paso 3: Lee la línea completa introducida por el usuario y la guarda en la variable 'nombre'.
        String nombre = s.nextLine();

        // Paso 4: Pide al usuario que ingrese un filtro para el apellido.
        System.out.print("Filtro para el apellido: ");
        // Paso 5: Lee y guarda el valor para 'apellido'.
        String apellido= s.nextLine();

        // Paso 6: Pide al usuario que ingrese un filtro para la forma de pago.
        System.out.print("Filtro para la forma de pago: ");
        // Paso 7: Lee y guarda el valor para 'formaPago'.
        String formaPago = s.nextLine();

        // --- 2. INICIALIZACIÓN DE JPA Y CRITERIA ---

        // Paso 1: Obtiene el EntityManager, el objeto principal para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        // Paso 2: Obtiene el CriteriaBuilder, la fábrica para construir los objetos de la consulta.
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Paso 3: Crea el objeto de consulta que devolverá una lista de objetos Cliente.
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        
        // Paso 4: Establece la entidad Cliente como el punto de partida de la consulta (cláusula FROM).
        Root<Cliente> from = query.from(Cliente.class);

        // --- 3. CONSTRUCCIÓN DINÁMICA DE PREDICADOS ---
        
        // Paso 1: Se crea una lista vacía para almacenar las condiciones (predicados) del WHERE.
        List<Predicate> condiciones = new ArrayList<>();

        // Paso 2: Si el usuario proporcionó un nombre (no es nulo ni está vacío)...
        if(nombre != null && !nombre.isEmpty()){
            // ...se crea un predicado de igualdad (nombre = ?) y se añade a la lista.
            condiciones.add(cb.equal(from.get("nombre"), nombre));
        }

        // Paso 3: Si el usuario proporcionó un apellido...
        if(apellido != null && !apellido.isEmpty()){
            // ...se crea un predicado de igualdad (apellido = ?) y se añade a la lista.
            condiciones.add(cb.equal(from.get("apellido"), apellido));
        }

        // Paso 4: Si el usuario proporcionó una forma de pago...
        if(formaPago != null && !formaPago.isEmpty()){
            // ...se crea un predicado de igualdad (formaPago = ?) y se añade a la lista.
            condiciones.add(cb.equal(from.get("formaPago"), formaPago));
        }

        // --- 4. CONSTRUCCIÓN DE LA CONSULTA FINAL ---

        // Paso 1: Se construye la consulta: se selecciona la entidad completa y se aplica la cláusula WHERE.
        // cb.and() une todos los predicados de la lista con el operador AND.
        // condiciones.toArray(...) convierte la lista a un arreglo, que es lo que espera el método and().
        query.select(from).where(cb.and(condiciones.toArray(new Predicate[condiciones.size()])));


        // --- 5. EJECUCIÓN Y VISUALIZACIÓN ---

        // Paso 1: Se ejecuta la consulta que ha sido construida dinámicamente.
        List<Cliente> clientes = em.createQuery(query).getResultList();

        // Paso 2: Se imprime cada cliente encontrado en la consola.
        System.out.println("--- Resultados de la Búsqueda ---");
        clientes.forEach(System.out::println);

        // --- 6. CIERRE DE RECURSOS ---

        // Paso 1: Se cierra el EntityManager para liberar la conexión y otros recursos.
        em.close();

    }
}