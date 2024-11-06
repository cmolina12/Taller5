package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class ComboTest 
{

    private Combo comboSinDescuento;
    private Combo comboConDescuento;
    private Combo comboDescuentoCompleto;
    private ProductoMenu hamburguesa;
    private ProductoMenu papas;
    private ProductoMenu bebida;

    @BeforeEach
    public void setUp(){
        hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        papas = new ProductoMenu("Papas", 5000);
        bebida = new ProductoMenu("Bebida", 3000);

        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(hamburguesa);
        productos.add(papas);
        productos.add(bebida);

        comboSinDescuento = new Combo("Combo sin descuento", 0, productos);
        comboConDescuento = new Combo("Combo con descuento 50%", 0.5, productos);
        comboDescuentoCompleto = new Combo("Combo con descuento completo (Gratis)", 1.0, productos);



    }

    @Test
    public void testGetNombre(){
        assertEquals("Combo sin descuento", comboSinDescuento.getNombre());
        assertEquals("Combo con descuento 50%", comboConDescuento.getNombre());
        assertEquals("Combo con descuento completo (Gratis)", comboDescuentoCompleto.getNombre());
    }

    @Test
    public void testGetPrecio(){
        assertEquals(18000, comboSinDescuento.getPrecio(), "El precio del combo sin descuento debería ser 18000");
        assertEquals(9000, comboConDescuento.getPrecio(), "El precio del combo con descuento 50% debería ser 9000");
        assertEquals(0, comboDescuentoCompleto.getPrecio(), "El precio del combo con descuento completo debería ser 0");
    }

    @Test
    public void testGenerarTextoFactura(){
        String facturaSinDescuento = comboSinDescuento.generarTextoFactura();
        String facturaConDescuento = comboConDescuento.generarTextoFactura();
        String facturaDescuentoCompleto = comboDescuentoCompleto.generarTextoFactura();

        assertTrue(facturaSinDescuento.contains("Combo sin descuento"), "La factura del combo sin descuento debería contener el nombre del combo");
        assertTrue(facturaSinDescuento.contains("0.0"), "La factura del combo sin descuento debería contener el descuento del combo");
        assertTrue(facturaSinDescuento.contains("18000"), "La factura del combo sin descuento debería contener el precio del combo");
    
        assertTrue(facturaConDescuento.contains("Combo con descuento 50%"), "La factura del combo con descuento 50% debería contener el nombre del combo");
        assertTrue(facturaConDescuento.contains("0.5"), "La factura del combo con descuento 50% debería contener el descuento del combo");
        assertTrue(facturaConDescuento.contains("9000"), "La factura del combo con descuento 50% debería contener el precio del combo");

        assertTrue(facturaDescuentoCompleto.contains("Combo con descuento completo (Gratis)"), "La factura del combo con descuento completo debería contener el nombre del combo");
        assertTrue(facturaDescuentoCompleto.contains("1.0"), "La factura del combo con descuento completo debería contener el descuento del combo");
        assertTrue(facturaDescuentoCompleto.contains("0"), "La factura del combo con descuento completo debería contener el precio del combo");
    
    }


        
}
