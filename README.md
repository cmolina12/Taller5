---

# **Taller 5: Restaurante de Hamburguesas**

## **Descripción**

Este proyecto implementa un sistema de gestión para un restaurante de hamburguesas. Permite cargar información inicial, gestionar pedidos, agregar productos o combos, generar facturas, y manejar excepciones. La interacción se realiza a través de una aplicación de consola.

---

## **Instrucciones para Usar la Aplicación**

### **Requisitos Previos**
1. **Archivos de datos**: 
   - Asegúrate de que los archivos `ingredientes.txt`, `menu.txt` y `combos.txt` estén en la carpeta `data` del proyecto.

### **Ejecutar la Aplicación**
1. Ejecuta la clase `Aplicacion` desde Eclipse (Run as Java Appliaction).
2. Sigue las instrucciones en consola.

### **Opciones del Menú**

1. **Cargar Información del Restaurante**
   - Carga los ingredientes, productos básicos y combos desde los archivos de la carpeta `data`.
   - **Recomendación**: Realiza esta acción antes de iniciar un pedido.

2. **Iniciar un Nuevo Pedido**
   - Ingresa el nombre y la dirección del cliente para iniciar un pedido.
   - **Restricción**: No puedes iniciar un nuevo pedido si ya hay uno en curso.

3. **Agregar Producto al Pedido**
   - Selecciona un producto o combo del menú mostrado en consola.
   - **Restricción**: Debes iniciar un pedido antes de agregar productos.

4. **Cerrar y Guardar Pedido**
   - Finaliza el pedido en curso y guarda la factura en la carpeta `facturas`.
   - Muestra la factura en consola, con el precio neto, IVA y precio total.

5. **Mostrar el Menú del Restaurante**
   - Consulta los productos básicos y combos disponibles.

6. **Salir**
   - Finaliza la aplicación.


