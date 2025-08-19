# Resultado

============ Consultar todos ============
Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_
id=1, nombre='Andres', apellido='Guzman', formaPago='debito
id=2, nombre='John', apellido='Doe', formaPago='credito
id=4, nombre='Pepa', apellido='Doe', formaPago='credito
id=6, nombre='Luna', apellido='Garcia', formaPago='debito

============ Consultar todos ============
Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ where cliente0_.id=?
id=1, nombre='Andres', apellido='Guzman', formaPago='debito

============ Consulta solo el nombre por el id ============
Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ where cliente0_.id=?
id=2, nombre='John', apellido='Doe', formaPago='credito

============ Consultas por campo personalizado ============
Hibernate: select cliente0_.id as col_0_0_, cliente0_.nombre as col_1_0_, cliente0_.apellido as col_2_0_ from clientes cliente0_ where cliente0_.id=?
id = 1
Nombre: Andres
Apellido: Guzman

============ Consultas por campo personalizado lista ============
Hibernate: select cliente0_.id as col_0_0_, cliente0_.nombre as col_1_0_, cliente0_.apellido as col_2_0_ from clientes cliente0_
id = 1| nombre = Andres| apellido = Guzman
id = 2| nombre = John| apellido = Doe
id = 4| nombre = Pepa| apellido = Doe
id = 6| nombre = Luna| apellido = Garcia

Process finished with exit code 0
