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
	public void testGenerarTextoFactura(){

		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(papas);
		pedido.agregarProducto(bebida);

		String factura = pedido.generarTextoFactura();
		String facturaEsperada = "Cliente: Camilo Molina\nDirección: Calle 20 #2a-51\n\nProductos:\n\nHamburguesa - $10000\nPapas - $5000\nBebida - $3000\n\nTotal: $21420\n";
		assertEquals(facturaEsperada, factura, "La factura no es la esperada.");

		pedido.agregarProducto(combo);
		factura = pedido.generarTextoFactura();
		facturaEsperada = "Cliente: Camilo Molina\nDirección: Calle 20 #2a-51\n\nProductos:\n\nHamburguesa - $10000\nPapas - $5000\nBebida - $3000\nCombo 1 - $34200\n\nTotal: $40698\n";
		assertEquals(facturaEsperada, factura, "La factura no es la esperada después de agregar un combo.");


	}

	@Test
	public void testGuardarFactura() throws FileNotFoundException {
		pedido.agregarProducto(hamburguesa);
		pedido.agregarProducto(combo);
	
		File archivoFactura = new File("factura_test.txt");
		pedido.guardarFactura(archivoFactura);
	
		// Leer el contenido del archivo para verificar
		Scanner scanner = new Scanner(archivoFactura);
		StringBuilder contenido = new StringBuilder();
		while (scanner.hasNextLine()) {
			contenido.append(scanner.nextLine()).append("\n");
		}
		scanner.close();
	
		String factura = contenido.toString();
	
		// Verificamos que los datos relevantes estén en la factura
		assertTrue(factura.contains("Cliente: Juan Pérez"), "El nombre del cliente no está en la factura.");
		assertTrue(factura.contains("Hamburguesa"), "El producto 'Hamburguesa' no está en la factura.");
		assertTrue(factura.contains("Combo Completo"), "El combo no está en la factura.");
		assertTrue(factura.contains("Precio Neto:  26200"), "El precio neto no es el esperado.");
		assertTrue(factura.contains("IVA:          " + (int) (26200 * 0.19)), "El IVA no es el esperado.");
		assertTrue(factura.contains("Precio Total: " + (int) (26200 * 1.19)), "El precio total no está en la factura.");
	
		// Eliminamos el archivo después de la prueba
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
