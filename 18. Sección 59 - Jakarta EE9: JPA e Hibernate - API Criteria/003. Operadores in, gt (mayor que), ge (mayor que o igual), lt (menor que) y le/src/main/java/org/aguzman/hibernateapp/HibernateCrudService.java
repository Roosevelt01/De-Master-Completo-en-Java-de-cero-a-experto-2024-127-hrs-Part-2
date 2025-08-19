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
        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("========== Lista ==========");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("\n========== Obtener por id ==========");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("\n========== Insertar nuevo cliente ==========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");

        service.guardar(cliente);
        System.out.println("Cliente guardado con éxito");
        service.listar().forEach(System.out::println);

        System.out.println("\n========== Editar cliente ==========");
        Long id = cliente.getId();
        optionalCliente =service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            System.out.println("Cliente editado con éxito!");
            service.listar().forEach(System.out::println);
        });

        System.out.println("\n========== Eliminar cliente ==========");
        id = cliente.getId();
        optionalCliente =service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado con éxito!");
            service.listar().forEach(System.out::println);
        });
        em.close();



    }
}
