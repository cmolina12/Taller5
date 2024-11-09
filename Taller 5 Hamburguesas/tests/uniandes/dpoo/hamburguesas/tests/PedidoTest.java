package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Combo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PedidoTest {

	private Pedido pedido;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private ProductoMenu bebida;
	private Combo combo;

	@BeforeEach
	
	public void setUp() {
		
		Pedido.reiniciarContadorPedidos();
		pedido = new Pedido("Camilo Molina", "Calle 20 #2a-51");
		hamburguesa = new ProductoMenu("Hamburguesa", 10000);
		papas = new ProductoMenu("Papas", 5000);
		bebida = new ProductoMenu("Bebida", 3000);

		ArrayList<ProductoMenu> productos = new ArrayList<>();
		productos.add(hamburguesa);
		productos.add(papas);
		productos.add(bebida);

		combo = new Combo("Combo 1", 0.1, productos); // 10% de descuento en el combo
	}

	@Test
	public void testGetIdPedido(){
		int idPedido = pedido.getIdPedido();
        assertEquals(0, idPedido, "El ID del pedido no es el esperado.");
	}

	@Test
	public void testGetNombreCliente(){
		String nombreCliente = pedido.getNombreCliente();
		assertEquals("Camilo Molina", nombreCliente, "El nombre del cliente no es el esperado.");
	}

	@Test
	public void testAgregarProducto(){
		pedido.agregarProducto(hamburguesa);
		int tamanio_producto = pedido.getSizeProductos();
        assertEquals(1, tamanio_producto, "El número de productos no es el esperado después de agregar uno.");

		pedido.agregarProducto(combo);
		tamanio_producto = pedido.getSizeProductos();
        assertEquals(2, tamanio_producto, "El número de productos no es el esperado después de agregar un combo.");
	}

	@Test
	public void getSizeProductos(){
		int tamanio_producto = pedido.getSizeProductos();
		assertEquals(0, tamanio_producto, "El número de productos no es el esperado.");

		pedido.agregarProducto(hamburguesa);
		tamanio_producto = pedido.getSizeProductos();
		assertEquals(1, tamanio_producto, "El número de productos no es el esperado después de agregar uno.");
	}

	@Test
	public void testGetPrecioTotalPedido(){
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
		pedido.agregarProducto(bebida);

		int precioTotal = pedido.getPrecioTotalPedido();
		assertEquals(21420, precioTotal, "El precio total del pedido no es el esperado.");

		pedido.agregarProducto(combo);
		precioTotal = pedido.getPrecioTotalPedido();
		assertEquals(40698, precioTotal, "El precio total del pedido no es el esperado después de agregar un combo.");
	}
	
	@Test
	
	public void testGetPrecioNetoPedido(){
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
		pedido.agregarProducto(bebida);

		int precioNeto = pedido.getPrecioNetoPedido();
		assertEquals(18000, precioNeto, "El precio neto del pedido no es el esperado.");

		pedido.agregarProducto(combo);
		precioNeto = pedido.getPrecioNetoPedido();
		assertEquals(34200, precioNeto, "El precio neto del pedido no es el esperado después de agregar un combo.");
	}

	@Test
	public void testGetPrecioIVAPedido(){
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
		pedido.agregarProducto(bebida);

		int precioIVA = pedido.getPrecioIVAPedido();
		assertEquals(3420, precioIVA, "El precio del IVA del pedido no es el esperado.");

		pedido.agregarProducto(combo);
		precioIVA = pedido.getPrecioIVAPedido();
		assertEquals(6498, precioIVA, "El precio del IVA del pedido no es el esperado después de agregar un combo.");
	}

	@Test
	public void testGenerarTextoFactura() {
	    // Agregar productos individuales
	    pedido.agregarProducto(hamburguesa);
	    pedido.agregarProducto(papas);
	    pedido.agregarProducto(bebida);

	    // Verificar factura antes del combo
	    String factura = pedido.generarTextoFactura();
	    String facturaEsperada = 
								"----------------\n"+
	    						"Cliente: Camilo Molina\n" +
	                             "Dirección: Calle 20 #2a-51\n" +
	                             "----------------\n" +
	                             "Hamburguesa\n" +
	                             "Precio base:            $10000\n" +
	                             "----------------\n" +
	                             "----------------\n"+
	                             "Papas\n" +
	                             "Precio base:            $5000\n" +
	                             "----------------\n" +
	                             "----------------\n"+
	                             "Bebida\n" +
	                             "Precio base:            $3000\n" +
	                             "----------------\n" +
	                             "----------------\n"+
	                             "Precio Neto:            $18000\n" +
	                             "IVA:                    $3420\n" +
	                             "Precio Total:           $21420\n" +
	                             "----------------\n";
	    assertEquals(facturaEsperada, factura, "La factura no es la esperada.");

	    // Agregar combo y verificar factura
	    pedido.agregarProducto(combo);
	    factura = pedido.generarTextoFactura();
	    facturaEsperada = 
						"----------------\n"+
	    				"Cliente: Camilo Molina\n" +
	                      "Dirección: Calle 20 #2a-51\n" +
	                      "----------------\n" +
	                      "Hamburguesa\n" +
	                      "Precio base:            $10000\n" +
	                      "----------------\n" +
                          "----------------\n"+
	                      "Papas\n" +
	                      "Precio base:            $5000\n" +
	                      "----------------\n" +
                          "----------------\n"+
	                      "Bebida\n" +
	                      "Precio base:            $3000\n" +
	                      "----------------\n" +
                          "----------------\n"+
	                      "Combo 1\n" +
	                      "Precio base sin descuento:            $18000\n" +
	                      "Descuento aplicado:                   10%\n" +
	                      "Precio final del combo:               $16200\n" +
	                      "----------------\n" +
                          "----------------\n"+
	                      "Precio Neto:            $34200\n" +
	                      "IVA:                    $6498\n" +
	                      "Precio Total:           $40698\n" +
	                      "----------------\n";
	    assertEquals(facturaEsperada, factura, "La factura no es la esperada después de agregar un combo.");
	}



	@Test
	public void testGuardarFactura() throws FileNotFoundException {
	    // Agregar productos al pedido
	    pedido.agregarProducto(hamburguesa);
	    pedido.agregarProducto(combo);

	    // Guardar la factura en un archivo
	    File archivoFactura = new File("factura_test.txt");
	    pedido.guardarFactura(archivoFactura);

	    // Leer el contenido del archivo generado
	    StringBuilder contenido = new StringBuilder();
	    try (Scanner scanner = new Scanner(archivoFactura)) {
	        while (scanner.hasNextLine()) {
	            contenido.append(scanner.nextLine()).append("\n");
	        }
	    }

	    // Factura esperada
	    String facturaEsperada = 
	    						"----------------\n"+
	    						"Cliente: Camilo Molina\n" +
	                             "Dirección: Calle 20 #2a-51\n" +
	                             "----------------\n" +
	                             "Hamburguesa\n" +
	                             "Precio base:            $10000\n" +
	                             "----------------\n" +
	                             "----------------\n"+
	                             "Combo 1\n" +
	                             "Precio base sin descuento:            $18000\n" +
	                             "Descuento aplicado:                   10%\n" +
	                             "Precio final del combo:               $16200\n" +
	                             "----------------\n" +
	                             "----------------\n"+
	                             "Precio Neto:            $26200\n" +
	                             "IVA:                    $4978\n" +
	                             "Precio Total:           $31178\n" +
	                             "----------------\n";

	    // Validar el contenido del archivo
	    assertEquals(facturaEsperada, contenido.toString(), "La factura guardada no coincide con la esperada.");

	    // Eliminar el archivo después de la prueba
	    archivoFactura.delete();
	}

	
	@AfterEach

	public void tearDown() {
		pedido = null;
		hamburguesa = null;
		papas = null;
		bebida = null;
		combo = null;
	}
	
	
}
