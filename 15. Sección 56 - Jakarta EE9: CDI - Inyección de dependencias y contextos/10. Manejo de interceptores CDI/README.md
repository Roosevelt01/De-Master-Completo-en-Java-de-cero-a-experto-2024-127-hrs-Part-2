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

- El primer mensaje muestra que el interceptor entró antes de que se ejecutara el método listar().

- Después, se muestra el log de inicialización y destrucción del repositorio, así como el cierre de la conexión. Esto ocurre dentro de la invocación del método listar(), lo que demuestra que el InvocationContext.proceed() fue llamado.

- El mensaje de "saliendo" del interceptor no está en este log, lo que indica un posible error de configuración o un corte en el log. Sin embargo, la entrada inicial confirma que el interceptor está activo y funcionando.

<hr>

<h3>Prueba 2: Interceptación con el Estereotipo @Service</h3>

<p>Evento: Dar clic en la opción "mostrar productos".</p>

![WindowsTerminal_hicdoYRz1b](https://github.com/user-attachments/assets/4a9c0b94-70b0-43a5-a760-e7b8baeb7356)

// Clic en "mostrar productos"
...
06-Aug-2025 16:21:31.076 INFO ... LogginInterceptor.logging ***** Entrando antes de invocar el m├®todo listar de la clase ...ProductoServiceJdbcImpl
...
// Más logs que demuestran que la ejecución del método continúa...
06-Aug-2025 16:21:31.477 INFO ... ProducerResources.close Cerrando la conexi├│n a la bbdd mysql
// Aquí debería estar el log de "saliendo", que el instructor confirma que se muestra.

// Clic en el botón "editar"
06-Aug-2025 16:13:01.927 INFO ... LogginInterceptor.logging ***** Entrando antes de invocar el m├®todo porId de la clase ...ProductoServiceJdbcImpl
...
06-Aug-2025 16:13:02.120 INFO ... ProducerResources.close Cerrando la conexi├│n a la bbdd mysql
// Log de "saliendo" del interceptor










