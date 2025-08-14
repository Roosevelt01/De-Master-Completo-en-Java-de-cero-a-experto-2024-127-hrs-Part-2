package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.services.ClienteService;
import org.aguzman.hibernateapp.services.ClienteServiceImpl;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class HibernateCrudService  {
    public static void main(String[] args) {
        // 1. Obtener el EntityManager. Este recurso es esencial para el Service y debe cerrarse al final.
        EntityManager em = JpaUtil.getEntityManager();

        // 2. Instanciar el servicio. El EntityManager se inyecta en el constructor del servicio.
        ClienteService service = new ClienteServiceImpl(em);

        // ====================================================================
        //                 Operación: Listar todos los clientes
        // ====================================================================
        System.out.println("========== Lista ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);// Utiliza una referencia a método de Java 8 para imprimir.

        // ====================================================================
        //                 Operación: Obtener cliente por ID
        // ====================================================================
        System.out.println("\n========== Obtener por id ==========");
        // Se utiliza Optional para manejar el caso de que el cliente no exista.
        Optional<Cliente> optionalCliente = service.porId(1L);
        // Si el Optional contiene un valor, se imprime.
        optionalCliente.ifPresent(System.out::println);

        // ====================================================================
        //                 Operación: Insertar un nuevo cliente
        // ====================================================================        
        System.out.println("\n========== Insertar nuevo cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");
        service.guardar(cliente);

        // Mostrar la lista actualizada para verificar el nuevo cliente
        System.out.println("Cliente guardado con éxito");
        service.listar().forEach(System.out::println);

        // ====================================================================
        //                 Operación: Editar un cliente existente
        // ====================================================================
        System.out.println("\n========== Editar cliente ==========");
        // Obtener el ID del cliente que acabamos de guardar.
        Long id = cliente.getId();
        optionalCliente =service.porId(id);

        // Si el cliente existe, se edita y se vuelve a guardar.
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            // Mostrar la lista para reflejar el cambio.
            System.out.println("Cliente editado con éxito!");
            service.listar().forEach(System.out::println);
        });

        // ====================================================================
        //                 Operación: Eliminar un cliente
        // ====================================================================
        System.out.println("\n========== Eliminar cliente ==========");
        id = cliente.getId();
        optionalCliente =service.porId(id);

        // Si el cliente existe, se elimina.
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            // Mostrar la lista para verificar la eliminación.
            System.out.println("Cliente eliminado con éxito!");
            service.listar().forEach(System.out::println);
        });
        em.close();
    }
}