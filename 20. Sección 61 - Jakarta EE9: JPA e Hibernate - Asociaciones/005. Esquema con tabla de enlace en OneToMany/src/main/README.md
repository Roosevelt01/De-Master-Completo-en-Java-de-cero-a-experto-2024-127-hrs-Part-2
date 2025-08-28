
#Análisis de los Resultados de Ejecución

El log de la consola es fundamental para entender el impacto de @JoinTable.

<h1>1. Generación del Esquema (DDL) con Tabla de Unión</h1>h1>

Hibernate: drop table if exists clientes
Hibernate: drop table if exists direcciones
Hibernate: drop table if exists tbl_clientes_direcciones // <- Eliminación de la tabla de unión
Hibernate: create table clientes (...)
Hibernate: create table direcciones (id bigint ..., calle varchar(255), ...) // <- Sin llave foránea
Hibernate: create table tbl_clientes_direcciones (id_cliente bigint not null, id_direccion bigint not null) // <- Creación de la tabla de unión
Hibernate: alter table tbl_clientes_direcciones add constraint UK... unique (id_direccion) // <- Restricción UNIQUE
Hibernate: alter table tbl_clientes_direcciones add constraint FK... foreign key (id_direccion) references direcciones (id)
Hibernate: alter table tbl_clientes_direcciones add constraint FK... foreign key (id_cliente) references clientes (id)

<h3>Análisis del Esquema:</h3>

- A diferencia del mapeo con @JoinColumn, la tabla direcciones ahora es una tabla "limpia", sin ninguna llave foránea.
- Hibernate crea una tercera tabla, tbl_clientes_direcciones, con los nombres personalizados que especificamos.
- Se añade una restricción UNIQUE a la columna id_direccion, garantizando a nivel de base de datos que una dirección no pueda ser asignada a más de un cliente.
- Se establecen las dos llaves foráneas que conectan la tabla de unión con clientes y direcciones.


