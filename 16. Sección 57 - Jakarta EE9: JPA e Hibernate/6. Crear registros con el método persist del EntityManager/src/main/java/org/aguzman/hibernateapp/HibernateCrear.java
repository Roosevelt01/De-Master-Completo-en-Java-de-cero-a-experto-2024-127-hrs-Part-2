package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {
        // Paso 1: Obtener una instancia del EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Paso 2: Obtener datos del usuario usando JOptionPane
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido: ");
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago: ");

            // Paso 3: Iniciar la transacción de la base de datos
            em.getTransaction().begin();

            // Paso 4: Crear un nuevo objeto Cliente y asignar los datos
            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);

            // Paso 5: Persistir el objeto en el contexto de persistencia de JPA
            em.persist(c);

            // Paso 6: Confirmar la transacción, lo que guarda los datos en la base de datos
            em.getTransaction().commit();

            System.out.println("El id del cliente registrado es: " + c.getId());
            
            // 7. Demostración del caché de primer nivel (No se ejecuta una nueva consulta SQL)
            c = em.find(Cliente.class, c.getId());
            System.out.println(c);
        }catch (Exception e){
            // Paso 8: En caso de error, hacer un rollback para revertir los cambios
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // Paso 9: Cerrar el EntityManager para liberar los recursos
            em.close();
        }
    }
}               