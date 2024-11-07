package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.*;
import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class RestauranteTest {
    
    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        

        restaurante.iniciarPedido("Camilo Molina", "Calle 20 #2a-51");
        Pedido pedido = restaurante.getPedidoEnCurso();

        assertNotNull(pedido, "El pedido no debe ser nulo");
        assertEquals("Camilo Molina", pedido.getNombreCliente(), "El nombre del cliente no es correcto");
        assertEquals("Calle 20 #2a-51", pedido.getDireccionCliente(), "La dirección del cliente no es correcta");
    }

    @Test
    public void testIniciarPedidoConPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Camilo Molina", "Calle 20 #2a-51");
        
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Otro Cliente", "Otra Dirección");
        }, "Se esperaba una excepción porque ya hay un pedido en curso.");}

    @Test
    public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException{
        restaurante.iniciarPedido("Camilo Molina", "Calle 20 #2a-51");
        restaurante.cerrarYGuardarPedido();

        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertNotNull(pedidos, "La lista de pedidos no debe ser nula.");
        assertEquals(1, pedidos.size(), "Debe haber un pedido en la lista de pedidos.");

        Pedido pedidoGuarado = pedidos.get(0);
        assertEquals("Camilo Molina", pedidoGuarado.getNombreCliente(), "El nombre del cliente no es correcto");
    }

    @Test
    public void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Se esperaba una excepción porque no hay un pedido en curso.");
    }

    @Test
    public void testGetPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        // Caso 1: Sin pedido en curso
        assertNull(restaurante.getPedidoEnCurso(), "No debe haber un pedido en curso.");

        // Caso 2: Con pedido en curso
        restaurante.iniciarPedido("Camilo Molina", "Calle 20 #2a-51");
        Pedido pedido = restaurante.getPedidoEnCurso();

        assertNotNull(pedido, "El pedido no debe ser nulo");
        assertEquals("Camilo Molina", pedido.getNombreCliente(), "El nombre del cliente no es correcto");
        assertEquals("Calle 20 #2a-51", pedido.getDireccionCliente(), "La dirección del cliente no es correcta");
    }

    @Test
    public void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        // Caso 1: Sin pedidos
        assertNotNull(restaurante.getPedidos(), "La lista de pedidos no debe ser nula.");
        assertEquals(0, restaurante.getPedidos().size(), "La lista de pedidos debe estar vacía.");

        // Caso 2: Pedido cerrado
        restaurante.iniciarPedido("Camilo Molina", "Calle 20 #2a-51");
        restaurante.cerrarYGuardarPedido();

        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertNotNull(pedidos, "La lista de pedidos no debe ser nula.");
        assertEquals(1, pedidos.size(), "Debe haber un pedido en la lista de pedidos.");

    
    }

    @Test
    public void testGetMenuBase(){
        // Caso inicial: Sin productos en el menú
        assertNotNull(restaurante.getMenuBase(), "El menú base no debe ser nulo.");
        assertEquals(0, restaurante.getPedidos().size(), "El menú base debe estar vacío.");

        // Caso 2: Con productos en el menú, simulacion de productos

        restaurante.getMenuBase().add(new ProductoMenu("Hamburguesa Sencilla", 10000));
        restaurante.getMenuBase().add(new ProductoMenu("Hamburguesa Doble", 15000));

        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        assertEquals(2, menuBase.size(), "El menú base debe tener dos productos.");
        assertEquals("Hamburguesa Sencilla", menuBase.get(0).getNombre(), "El nombre del producto no es correcto.");
        assertEquals(10000, menuBase.get(0).getPrecio(), "El precio del producto no es correcto.");
    }

    @Test
    public void testGetMenuCombos(){
        // Caso inicial: Sin combos en el menú

        assertNotNull(restaurante.getMenuCombos(), "El menú de combos no debe ser nulo.");
        assertEquals(0, restaurante.getMenuCombos().size(), "El menú de combos debe estar vacío.");

        // Caso 2: Con combos en el menú, simulacion de combos

        ArrayList<ProductoMenu> productosCombo1 = new ArrayList<ProductoMenu>();
        productosCombo1.add(new ProductoMenu("Hamburguesa Sencilla", 10000));
        productosCombo1.add(new ProductoMenu("Papas Fritas", 5000));
        Combo combo = new Combo("Combo 1", 0.1, productosCombo1);
        restaurante.getMenuCombos().add(combo);
    
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();
        assertEquals(1, menuCombos.size(), "El menú de combos debe tener un combo.");
        assertEquals("Combo 1", menuCombos.get(0).getNombre(), "El nombre del combo no es correcto.");
    
    }

    @Test
    public void testGetIngredientes(){
        // Caso inicial: Sin ingredientes :(

        assertNotNull(restaurante.getIngredientes(), "La lista de ingredientes no debe ser nula.");
        assertEquals(0, restaurante.getIngredientes().size(), "La lista de ingredientes debe estar vacía.");

        // Caso 2: Con ingredientes, simulacion de ingredientes

        restaurante.getIngredientes().add(new Ingrediente("Queso costeño sabroso", 200));
        restaurante.getIngredientes().add(new Ingrediente("Suerooooo", 100));

        ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
        assertEquals(2, ingredientes.size(), "La lista de ingredientes debe tener dos ingredientes.");
        assertEquals("Queso costeño sabroso", ingredientes.get(0).getNombre(), "El nombre del ingrediente no es correcto.");
        assertEquals(200, ingredientes.get(0).getCostoAdicional(), "El precio del ingrediente no es correcto.");

    }

    @Test
    public void testCargarInformacionRestaurante() throws IOException, HamburguesaException{

        // Cargamos de una la informacion de los archivos en vez de hacer un test por cada uno
        File ingredientes = new File("data/ingredientes.txt");
        File menu = new File("data/menuBase.txt");
        File combos = new File("data/menuCombos.txt");

        restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);

        // Ingredientes, menuBase y menuCombos deben tener información

        ArrayList<Ingrediente> ingredientesCargados = restaurante.getIngredientes();
        ArrayList<ProductoMenu> menuBase = restaurante.getMenuBase();
        ArrayList<Combo> menuCombos = restaurante.getMenuCombos();

        assertNotNull(ingredientesCargados, "La lista de ingredientes no debe ser nula.");
        assertNotNull(menuBase, "El menú base no debe ser nulo.");
        assertNotNull(menuCombos, "El menú de combos no debe ser nulo.");

        assertTrue(ingredientesCargados.size() > 0, "La lista de ingredientes debe tener información.");
        assertTrue(menuBase.size() > 0, "El menú base debe tener información.");
        assertTrue(menuCombos.size() > 0, "El menú de combos debe tener información.");
    }





}
