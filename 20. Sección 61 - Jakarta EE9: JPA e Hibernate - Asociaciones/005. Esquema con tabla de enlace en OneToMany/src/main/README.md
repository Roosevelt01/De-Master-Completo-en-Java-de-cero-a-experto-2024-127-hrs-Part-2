##Análisis de los Resultados de Ejecución

El log de la consola es fundamental para entender el impacto de @JoinTable.

<h1>1. Generación del Esquema (DDL) con Tabla de Unión</h1>

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

<h1>2. Carga de Datos y Ejecución de la Lógica (DML)</h1>

// ... (Logs de carga del import.sql) ...
Inicializar algo justo antesl del persist
Hibernate: insert into clientes (apellido, creado_en, editado_en, forma_pago, nombre) values (?, ?, ?, ?, ?)
Hibernate: insert into direcciones (calle, numero) values (?, ?)
Hibernate: insert into direcciones (calle, numero) values (?, ?)
Hibernate: insert into tbl_clientes_direcciones (id_cliente, id_direccion) values (?, ?)
Hibernate: insert into tbl_clientes_direcciones (id_cliente, id_direccion) values (?, ?)

<h3>Análisis de la Persistencia:</h3>

- Cuando se llama a em.persist(cliente), CascadeType.ALL provoca la siguiente secuencia de inserciones:

    1. Un INSERT en la tabla clientes para el nuevo cliente "Cata Edu".

    2. Dos INSERT en la tabla direcciones para las dos nuevas direcciones.

    3. Dos INSERT en la tabla de unión tbl_clientes_direcciones para registrar la asociación entre el nuevo cliente y sus dos nuevas direcciones. A diferencia del mapeo con @JoinColumn, aquí no se necesitan sentencias UPDATE.

<h1>3. Resultado en consola</h1>

-->  id=12 | nombre='Cata' | apellido='Edu' | ... | direcciones='[ | id=1 | calle='el vergel' | numero=123,  | id=2 | calle='vasco de gama' | numero=456]'

- Análisis: La salida del System.out.println(cliente) es la misma que antes. Esto demuestra que, aunque hemos cambiado drásticamente cómo se almacena la relación en la base de datos, la lógica de nuestra aplicación en Java permanece intacta, validando el poder de la capa de abstracción de JPA.




