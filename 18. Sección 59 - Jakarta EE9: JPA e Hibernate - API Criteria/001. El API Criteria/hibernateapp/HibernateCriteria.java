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
        EntityManager em = JpaUtil.getEntityManager();//Paso 1

        CriteriaBuilder criteria = em.getCriteriaBuilder();//Paso 2

        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);//Paso 3

        Root<Cliente> from = query.from(Cliente.class);//Paso 5

        query.select(from);//Paso 6

        List<Cliente> clientes = em.createQuery(query).getResultList();//Paso 7

        clientes.forEach(System.out::println);//Paso 8

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


        em.close();//Paso 14
    }
}
