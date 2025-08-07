# Análisis Detallado de las Pruebas de Ejecución

<p>Los logs del servidor demuestran claramente el comportamiento de los interceptores. Analicemos el orden de los mensajes para entender el flujo de ejecución.</p>

<h3>Prueba 1: Interceptación del Método listar()</h3>

<p>Evento: Dar clic en la opción "mostrar productos".</p>

![WindowsTerminal_UQXK4BsZv8](https://github.com/user-attachments/assets/490010d7-2139-4c8f-9f9b-be988579d81e)

Flujo de ejecución en el log:

06-Aug-2025 16:10:18.425 INFO ... LogginInterceptor.logging ***** Entrando antes de invocar el m├®todo listar de la clase ...ProductoServiceJdbcImpl
06-Aug-2025 16:10:18.426 INFO ... ProductoRepositoryJdbcImpl.inicializar Inicializando el beans ...ProductoRepositoryJdbcImpl
06-Aug-2025 16:10:18.626 INFO ... ProductoRepositoryJdbcImpl.destruir Destruyendo el beans ...ProductoRepositoryJdbcImpl
06-Aug-2025 16:10:18.629 INFO ... ProducerResources.close Cerrando la conexi├│n a la bbdd mysql

<h3>Análisis</h3>

- En el método listar(): El log muestra la entrada del interceptor para el método listar() del servicio ProductoServiceJdbcImpl, lo que confirma que el estereotipo @Service ha funcionado correctamente.

- En el método porId(): Al hacer clic en "editar", que invoca el método porId(), el log muestra una nueva entrada del interceptor. Esto demuestra que la funcionalidad de logging se aplicó automáticamente a todos los métodos de la clase ProductoServiceJdbcImpl al usar el estereotipo.

<hr>

<h3>Prueba 1: Interceptación del Método listar()</h3>

<p>Evento: Dar clic en la opción "mostrar productos".</p>

![WindowsTerminal_UQXK4BsZv8](https://github.com/user-attachments/assets/490010d7-2139-4c8f-9f9b-be988579d81e)

Flujo de ejecución en el log:

06-Aug-2025 16:10:18.425 INFO ... LogginInterceptor.logging ***** Entrando antes de invocar el m├®todo listar de la clase ...ProductoServiceJdbcImpl
06-Aug-2025 16:10:18.426 INFO ... ProductoRepositoryJdbcImpl.inicializar Inicializando el beans ...ProductoRepositoryJdbcImpl
06-Aug-2025 16:10:18.626 INFO ... ProductoRepositoryJdbcImpl.destruir Destruyendo el beans ...ProductoRepositoryJdbcImpl
06-Aug-2025 16:10:18.629 INFO ... ProducerResources.close Cerrando la conexi├│n a la bbdd mysql

<h3>Análisis</h3>

- En el método listar(): El log muestra la entrada del interceptor para el método listar() del servicio ProductoServiceJdbcImpl, lo que confirma que el estereotipo @Service ha funcionado correctamente.

- En el método porId(): Al hacer clic en "editar", que invoca el método porId(), el log muestra una nueva entrada del interceptor. Esto demuestra que la funcionalidad de logging se aplicó automáticamente a todos los métodos de la clase ProductoServiceJdbcImpl al usar el estereotipo.










