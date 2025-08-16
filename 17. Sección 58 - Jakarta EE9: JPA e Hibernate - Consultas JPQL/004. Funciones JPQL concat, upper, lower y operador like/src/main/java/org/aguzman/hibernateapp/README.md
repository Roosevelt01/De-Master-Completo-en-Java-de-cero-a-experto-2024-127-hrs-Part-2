# Resultado

============ Consulta con nombre y apellido concatenados ============
Hibernate: select concat(cliente0_.nombre, ' ', cliente0_.apellido) as col_0_0_ from clientes cliente0_
Andres Guzman
John Doe
Pepa Doe
Luna Garcia
John Roe

============ Consulta con nombre y apellido concatenados con maýúsculas ============
Hibernate: select upper(concat(cliente0_.nombre, ' ', cliente0_.apellido)) as col_0_0_ from clientes cliente0_
ANDRES GUZMAN
JOHN DOE
PEPA DOE
LUNA GARCIA
JOHN ROE


============ Consulta con nombre y apellido concatenados en minúsculas ============
Hibernate: select lower(concat(cliente0_.nombre, ' ', cliente0_.apellido)) as col_0_0_ from clientes cliente0_
andres guzman
john doe
pepa doe
luna garcia
john roe


============ Consulta para buscar por nombre ============

Con el param "andres"

Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ where cliente0_.nombre like ?
id=1, nombre='Andres', apellido='Guzman', formaPago='debito

con el param "and"

Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ where cliente0_.nombre like ?
id=1, nombre='Andres', apellido='Guzman', formaPago='debito
