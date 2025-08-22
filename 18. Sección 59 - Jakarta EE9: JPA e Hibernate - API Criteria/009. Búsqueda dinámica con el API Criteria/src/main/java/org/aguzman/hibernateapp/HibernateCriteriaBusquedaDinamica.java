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

        Scanner s = new Scanner(System.in);

        System.out.print("Filtro para el nombre: ");
        String nombre = s.nextLine();

        System.out.print("Filtro para el apellido: ");
        String apellido= s.nextLine();

        System.out.print("Filtro para la forma de pago: ");
        String formaPago = s.nextLine();

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate> condiciones = new ArrayList<>();

        if(nombre != null && !nombre.isEmpty()){
            condiciones.add(cb.equal(from.get("nombre"), nombre));
        }

        if(apellido != null && !apellido.isEmpty()){
            condiciones.add(cb.equal(from.get("apellido"), apellido));
        }

        if(formaPago != null && !formaPago.isEmpty()){
            condiciones.add(cb.equal(from.get("formaPago"), formaPago));
        }

        query.select(from).where(cb.and(condiciones.toArray(new Predicate[condiciones.size()])));

        List<Cliente> clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        em.close();




    }
}
