package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;

public class HibernateEditar {
    public static void main(String[] args) {
        // Paso 1: Obtiene una instancia del EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Paso 2: Solicita al usuario el ID del cliente que desea modificar.
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a modificar: "));
            
            // Paso 3: Busca el cliente en la base de datos usando em.find().
            // Si el cliente no se encuentra, 'c' será null.
            Cliente c = em.find(Cliente.class, id);

            // Paso 5: Solicita al usuario los nuevos datos, mostrando los datos actuales como valor por defecto.
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido: ", c.getApellido());
            String pago = JOptionPane.showInputDialog("Ingrese el método del págo: ", c.getFormaPago());

            // 6. Inicia una nueva transacción.
            em.getTransaction().begin();

            // 7. Actualiza los atributos del objeto 'c' con los nuevos valores.
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);

            // 8. Utiliza em.merge(c) para sincronizar los cambios.
            // Si el objeto 'c' ya está en el contexto de persistencia, se actualizan sus valores.
            // Si no está, 'merge' lo adjunta al contexto o crea uno nuevo.
            em.merge(c);

            // 9. Confirma la transacción. Aquí se ejecuta la sentencia UPDATE en la base de datos.
            em.getTransaction().commit();

            // 10. Muestra el objeto cliente actualizado en la consola.
            System.out.println(c);
        }catch(Exception e){
            // 11. En caso de error, si la transacción está activa, se revierte.
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            // 12. En cualquier caso, el EntityManager se cierra para liberar los recursos.
            em.close();
        }
    }
}     
