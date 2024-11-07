package uniandes.dpoo.hamburguesas.app;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.excepciones.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Aplicacion {
    private Restaurante restaurante;
    private Scanner scanner;

    public Aplicacion() {
        restaurante = new Restaurante();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Aplicacion app = new Aplicacion();
        app.ejecutarAplicacion();
    }

    public void ejecutarAplicacion() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            try {
                switch (opcion) {
                    case 1:
                        cargarInformacion();
                        break;
                    case 2:
                        iniciarPedido();
                        break;
                    case 3:
                        agregarProductoAlPedido();
                        break;
                    case 4:
                        cerrarPedido();
                        break;
                    case 5:
                        mostrarMenuRestaurante();
                        break;
                    case 6:
                        continuar = false;
                        System.out.println("Gracias por usar la aplicación.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú de opciones ---");
        System.out.println("1. Cargar información del restaurante");
        System.out.println("2. Iniciar un nuevo pedido");
        System.out.println("3. Agregar producto al pedido");
        System.out.println("4. Cerrar y guardar pedido");
        System.out.println("5. Mostrar el menú del restaurante");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void cargarInformacion() throws IOException, HamburguesaException {
        System.out.println("Cargando información del restaurante...");
        File ingredientes = new File("data/ingredientes.txt");
        File menuBase = new File("data/menu.txt");
        File combos = new File("data/combos.txt");

        restaurante.cargarInformacionRestaurante(ingredientes, menuBase, combos);
        System.out.println("Información cargada correctamente.");
    }

    private void iniciarPedido() throws YaHayUnPedidoEnCursoException {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccionCliente = scanner.nextLine();

        restaurante.iniciarPedido(nombreCliente, direccionCliente);
        System.out.println("Pedido iniciado para " + nombreCliente + ".");
    }

    private void agregarProductoAlPedido() throws NoHayPedidoEnCursoException {
        if (restaurante.getPedidoEnCurso() == null) {
            throw new NoHayPedidoEnCursoException();
        }

        System.out.println("Seleccione un producto o combo del menú:");
        mostrarMenuRestaurante();
        System.out.print("Ingrese el número del producto o combo: ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir salto de línea

        int menuBaseSize = restaurante.getMenuBase().size();

        if (indice < 0 || indice >= (menuBaseSize + restaurante.getMenuCombos().size())) {
            System.out.println("Opción no válida.");
            return;
        }

        if (indice < menuBaseSize) {
            // Es un producto básico
            ProductoMenu producto = restaurante.getMenuBase().get(indice);
            restaurante.getPedidoEnCurso().agregarProducto(producto);
            System.out.println("Producto agregado: " + producto.getNombre());
        } else {
            // Es un combo
            int comboIndex = indice - menuBaseSize;
            Combo combo = restaurante.getMenuCombos().get(comboIndex);
            restaurante.getPedidoEnCurso().agregarProducto(combo);
            System.out.println("Combo agregado: " + combo.getNombre());
        }
    }


    private void cerrarPedido() throws NoHayPedidoEnCursoException, IOException {
        if (restaurante.getPedidoEnCurso() == null) {
            throw new NoHayPedidoEnCursoException();
        }

        restaurante.cerrarYGuardarPedido();

        // Mostrar factura en consola
        System.out.println("\nFactura generada:");
        System.out.println(restaurante.getPedidos().get(restaurante.getPedidos().size() - 1).generarTextoFactura());

        System.out.println("Pedido cerrado y factura guardada.");
    }


    private void mostrarMenuRestaurante() {
        System.out.println("\n--- Menú del restaurante ---");
        int indice = 1;

        System.out.println("Productos básicos:");
        for (ProductoMenu producto : restaurante.getMenuBase()) {
            System.out.println(indice + ". " + producto.getNombre() + " - $" + producto.getPrecio());
            indice++;
        }

        System.out.println("\nCombos:");
        for (Combo combo : restaurante.getMenuCombos()) {
            System.out.println(indice + ". " + combo.getNombre());
            indice++;
        }
    }

}
