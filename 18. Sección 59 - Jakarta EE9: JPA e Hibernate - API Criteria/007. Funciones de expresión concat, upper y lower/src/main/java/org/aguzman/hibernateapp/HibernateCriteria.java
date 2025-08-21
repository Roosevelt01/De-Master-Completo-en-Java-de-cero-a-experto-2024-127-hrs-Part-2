package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder criteria = em.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);

        Root<Cliente> from = query.from(Cliente.class);

        query.select(from);

        List<Cliente> clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        System.out.println("\n========== Listar Where equals ==========");
//        query = criteria.createQuery(Cliente.class);//Paso 9
//        from = query.from(Cliente.class);//Paso 10
//        query.select(from).where(criteria.equal(from.get("nombre"), "Andres"));//Paso 11
//        clientes = em.createQuery(query).getResultList();//Paso 12
//        clientes.forEach(System.out::println);//Paso 13

        //Alternativa
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));

        clientes = em.createQuery(query).setParameter("nombre","Andres").getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Usando where like para buscar clientes por nombre ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.like(from.get("nombre"), "%and%"));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParamLike = criteria.parameter(String.class, "nombreParam");
        query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));
        clientes = em.createQuery(query).setParameter("nombreParam", "%jo%").getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Usando usando where between para rangos ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consulta where in ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        //query.select(from).where(from.get("nombre").in("Andres", "John", "Lou"));
        query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));//Alternativ
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");
        query.select(from).where(from.get("nombre").in(listParam));
        clientes = em.createQuery(query)
                .setParameter("nombres", Arrays.asList("Andres", "John", "Lou"))
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Filtrar ge ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.ge(from.get("id"), 3L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Filtrar gt ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Filtrar con le ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.le(from.get("id"), 2L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Filtrar con lt ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.lt(from.get("id"), 2L ));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consulta con los predicados conjunción(and) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago = criteria.equal(from.get("formaPago"), "debito");
        query.select(from).where(criteria.and(porNombre, porFormaPago));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consulta con los predicados disyunsión(OR) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre2 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago2 = criteria.equal(from.get("formaPago"), "debito");
        query.select(from).where(criteria.or(porNombre2, porFormaPago2));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consulta con los predicados disyunsión(OR) ==========");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre3 = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaPago3 = criteria.equal(from.get("formaPago"), "debito");
        Predicate p3 = criteria.ge(from.get("id"), 4L);
        query.select(from).where(criteria.and(p3, criteria.or(porNombre3, porFormaPago3)));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consultas con order by asc ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consultas con order by desc ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(criteria.desc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Consultas por id ==========");

        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");

        query.select(from).where(criteria.equal(from.get("id"), idParam));

        Cliente cliente = em.createQuery(query)
                .setParameter("id", 1L)
                .getSingleResult();

        System.out.println(cliente);

        System.out.println("\n========== Consulta solo el nombre de los clientes ==========");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(from.get("nombre"));
        List<String> nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("\n========== Consulta solo el nombre de los clientes únicos con distinct ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        //Ejemplo 1
        System.out.println("\n========== Consulta por nombres y apellidos concatenados ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido")));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        //Ejemplo 2
        System.out.println("\n========== Consulta por nombres y apellidos concatenados en con upper ==========");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);

        queryString.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        em.close();
    }
}
