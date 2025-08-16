# Resultado

============ Consulta por cliente y forma de pago ============
Hibernate: select cliente0_.id as col_0_0_, cliente0_.forma_pago as col_1_0_, cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_
formaPago = debito, id=1, nombre='Andres', apellido='Guzman', formaPago='debito
formaPago = credito, id=2, nombre='John', apellido='Doe', formaPago='credito
formaPago = credito, id=4, nombre='Pepa', apellido='Doe', formaPago='credito
formaPago = debito, id=6, nombre='Luna', apellido='Garcia', formaPago='debito

============ Consulta que devuelve objeto entity de una clase personalizada ============
Hibernate: select cliente0_.nombre as col_0_0_, cliente0_.apellido as col_1_0_ from clientes cliente0_
id=null, nombre='Andres', apellido='Guzman', formaPago='null
id=null, nombre='John', apellido='Doe', formaPago='null
id=null, nombre='Pepa', apellido='Doe', formaPago='null
id=null, nombre='Luna', apellido='Garcia', formaPago='null

============ Consulta que devuelve objeto Dto de una clase personalizada ============
Hibernate: select cliente0_.nombre as col_0_0_, cliente0_.apellido as col_1_0_ from clientes cliente0_
nombre='Andres | apellido='Guzman
nombre='John | apellido='Doe
nombre='Pepa | apellido='Doe
nombre='Luna | apellido='Garcia

Process finished with exit code 0