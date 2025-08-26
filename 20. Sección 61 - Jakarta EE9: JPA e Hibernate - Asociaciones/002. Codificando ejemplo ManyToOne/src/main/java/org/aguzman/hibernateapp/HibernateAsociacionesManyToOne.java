package org.aguzman.hibernateapp;

import org.aguzman.hibernateapp.entity.Cliente;
import org.aguzman.hibernateapp.entity.Factura;
import org.aguzman.hibernateapp.util.JpaUtil;

import javax.persistence.EntityManager;

public class HibernateAsociacionesManyToOne {
    public static void main(String[] args) {
        // Paso 1: Obtiene el EntityManager para interactuar con la base de datos.
        EntityManager em = JpaUtil.getEntityManager();

        try{
            // Paso 2: Inicia una nueva transacción. Todas las operaciones de escritura deben estar dentro de una.
            em.getTransaction().begin();
            
            // Paso 3: Crear y persistir un nuevo cliente
            Cliente cliente = new Cliente("Cara", "Edu");
            cliente.setFormaPago("credito");            
            em.persist(cliente);

            //Paso 4: Crear una nueva factura y asociarla al cliente
            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);// Establece la relación

            em.persist(factura);
            
            //Paso 5: Imprimir el resultado
            System.out.println("\n"+factura);
            System.out.println("\n"+factura.getCliente());
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}

// ... (logs de inicio de Hibernate) ...
// ADVERTENCIA INOFENSIVA: Hibernate intenta borrar una llave foránea que no existe la primera vez.
WARN: GenerationTarget encountered exception ... Caused by: java.sql.SQLSyntaxErrorException: Table '...' doesn't exist

// --- CREACIÓN DEL ESQUEMA ---
Hibernate: drop table if exists clientes
Hibernate: drop table if exists facturar
Hibernate: create table clientes (...)
// Se crea la tabla facturar con la columna personalizada 'id_cliente'.
Hibernate: create table facturar (id bigint ..., id_cliente bigint, ...)
// Se añade la restricción de llave foránea.
Hibernate: alter table facturar add constraint ... foreign key (id_cliente) references clientes (id)

// --- EJECUCIÓN DEL CÓDIGO ---
// Callback del @PrePersist del nuevo cliente.
Inicializar algo justo antesl del persist
// Se ejecuta el INSERT para el nuevo cliente.
Hibernate: insert into clientes (apellido, creado_en, editado_en, forma_pago, nombre) values (?, ?, ?, ?, ?)
// Se ejecuta el INSERT para la nueva factura, incluyendo la llave foránea (id_cliente).
Hibernate: insert into facturar (id_cliente, descripción, total) values (?, ?, ?)
// Salida del System.out.println(factura).
id=1, descripcion='compras de supermercado', total=2000, cliente=id=1, nombre='Cata', ...