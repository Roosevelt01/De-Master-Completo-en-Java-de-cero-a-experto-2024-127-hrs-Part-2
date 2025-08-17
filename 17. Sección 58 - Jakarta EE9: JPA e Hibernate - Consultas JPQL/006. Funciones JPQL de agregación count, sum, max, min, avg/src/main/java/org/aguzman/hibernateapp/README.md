# Resultado

============ Consulta con orden ============

asc

Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ order by cliente0_.nombre asc
id=1, nombre='Andres', apellido='Guzman', formaPago='debito
id=2, nombre='John', apellido='Doe', formaPago='credito
id=8, nombre='John', apellido='Roe', formaPago='paypal
id=6, nombre='Luna', apellido='Garcia', formaPago='debito
id=4, nombre='Pepa', apellido='Doe', formaPago='crédito


desc

Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ order by cliente0_.nombre desc
id=4, nombre='Pepa', apellido='Doe', formaPago='credito
id=6, nombre='Luna', apellido='Garcia', formaPago='debito
id=2, nombre='John', apellido='Doe', formaPago='credito
id=8, nombre='John', apellido='Roe', formaPago='paypal
id=1, nombre='Andres', apellido='Guzman', formaPago='debito

by cliente0_.nombre desc, cliente0_.apellido desc


Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ order by cliente0_.nombre desc, cliente0_.apellido desc
id=4, nombre='Pepa', apellido='Doe', formaPago='credito
id=6, nombre='Luna', apellido='Garcia', formaPago='debito
id=8, nombre='John', apellido='Roe', formaPago='paypal
id=2, nombre='John', apellido='Doe', formaPago='credito
id=1, nombre='Andres', apellido='Guzman', formaPago='debito

cliente0_.nombre asc, cliente0_.apellido desc

Hibernate: select cliente0_.id as id1_0_, cliente0_.apellido as apellido2_0_, cliente0_.forma_pago as forma_pa3_0_, cliente0_.nombre as nombre4_0_ from clientes cliente0_ order by cliente0_.nombre asc, cliente0_.apellido desc
id=1, nombre='Andres', apellido='Guzman', formaPago='debito
id=8, nombre='John', apellido='Roe', formaPago='paypal
id=2, nombre='John', apellido='Doe', formaPago='credito
id=6, nombre='Luna', apellido='Garcia', formaPago='debito
id=4, nombre='Pepa', apellido='Doe', formaPago='credito

