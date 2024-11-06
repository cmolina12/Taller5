package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import org.junit.jupiter.api.AfterEach;



public class ProductoMenuTest {
    
    private ProductoMenu productoMenu;

    @BeforeEach
    public void setUp() {
        productoMenu = new ProductoMenu("Hamburguesa", 10000);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa", productoMenu.getNombre(), "El nombre del producto no es el esperado.");
    }

    @Test
    public void testGetPrecio() {
        assertEquals(10000, productoMenu.getPrecio(), "El precio del producto no es el esperado.");
    }

    @Test
    public void testGenerarTextoFactura() {
        assertEquals("Hamburguesa\n            10000\n", productoMenu.generarTextoFactura(), "El texto de la factura no es el esperado.");
    }

   @AfterEach
    public void tearDown() {
        productoMenu = null;
    } 
}
