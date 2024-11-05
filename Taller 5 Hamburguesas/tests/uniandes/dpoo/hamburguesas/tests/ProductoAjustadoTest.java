package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {
    
    private ProductoAjustado productoAjustado;
    private Ingrediente queso;
    private Ingrediente tomate;

    @BeforeEach
    public void setUp() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 10000);
        productoAjustado = new ProductoAjustado(productoBase);
        queso = new Ingrediente("Queso", 2000);
        tomate = new Ingrediente("Tomate", 1000);
    }

    @Test
    void testPrecioConUnIngrediente() {
        productoAjustado.agregarIngrediente(queso);
        assertEquals(12000, productoAjustado.getPrecio(), "El precio con un ingrediente adicional no es el esperado.");
    }

    @Test
    void testPrecioConMultiplesIngredientes() {
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.agregarIngrediente(tomate);
        assertEquals(13000, productoAjustado.getPrecio(), "El precio con múltiples ingredientes adicionales no es el esperado.");
    }

    @Test
    void testEliminarIngredienteNoExistente() {
        productoAjustado.eliminarIngrediente(tomate); // Tomate no fue agregado
        assertEquals(10000, productoAjustado.getPrecio(), "El precio cambió al intentar eliminar un ingrediente no agregado.");
    }

    @Test
    void testAgregarYEliminarElMismoIngrediente() {
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.eliminarIngrediente(queso);
        assertEquals(10000, productoAjustado.getPrecio(), "El precio cambió incorrectamente al agregar y eliminar el mismo ingrediente.");
    }

    @Test
    void testGenerarTextoFactura() {
        productoAjustado.agregarIngrediente(queso);
        productoAjustado.eliminarIngrediente(tomate);  // Tomate no afecta el precio
        String factura = productoAjustado.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("+Queso"));
        assertTrue(factura.contains("-Tomate"));
        assertTrue(factura.contains("12000"), "El texto de la factura no contiene el precio esperado.");
    }

    @After
    public void tearDown() {
        productoAjustado = null;
        queso = null;
        tomate = null;
    }

}
