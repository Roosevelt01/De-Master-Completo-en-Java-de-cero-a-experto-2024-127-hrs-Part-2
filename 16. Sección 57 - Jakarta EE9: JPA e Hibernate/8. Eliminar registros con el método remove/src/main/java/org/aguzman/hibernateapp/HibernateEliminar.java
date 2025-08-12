package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {
        // 1. Crear un objeto Scanner para leer la entrada del usuario.
        Scanner s = new Scanner(System.in);
        // 2. Pedir al usuario el ID del cliente a eliminar.
        System.out.print("Ingrese el id del cliente a eliminar: ");
        // 3. Leer el ID ingresado por el usuario.
        Long id = s.nextLong();
        
        // 4. Obtener una instancia del EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // 5. Buscar el cliente en la base de datos usando em.find().
            // Esto asegura que el objeto esté en estado "Managed".
            Cliente cliente = em.find(Cliente.class, id);
            
            // 6. Iniciar la transacción.
            em.getTransaction().begin();
            
            // 7. Marcar la entidad para su eliminación con em.remove().
            // Esto solo se puede hacer si el objeto está en estado "Managed".
            em.remove(cliente);
            
            // 8. Confirmar la transacción, lo que ejecuta la sentencia DELETE.
            em.getTransaction().commit();
        }catch(Exception e){
            // 9. En caso de error, hacer un rollback.
            em.getTransaction().rollback();
            // 10. Imprimir la traza de la excepción para depuración
            e.printStackTrace();
        }finally {
            // 11. Cerrar el Scanner.
            em.close();
        }

    }
}