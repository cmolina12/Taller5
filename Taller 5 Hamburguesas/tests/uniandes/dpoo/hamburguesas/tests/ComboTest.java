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
    public void testGenerarTextoFactura() {
        String facturaSinDescuento = comboSinDescuento.generarTextoFactura();
        String facturaConDescuento = comboConDescuento.generarTextoFactura();
        String facturaDescuentoCompleto = comboDescuentoCompleto.generarTextoFactura();

        String facturaEsperadaSinDescuento = "Combo sin descuento\n" +
                                             "Precio base sin descuento:            $18000\n" +
                                             "Descuento aplicado:                   0%\n" +
                                             "Precio final del combo:               $18000\n"+
                                             ("----------------\n");

        String facturaEsperadaConDescuento = "Combo con descuento 50%\n" +
                                             "Precio base sin descuento:            $18000\n" +
                                             "Descuento aplicado:                   50%\n" +
                                             "Precio final del combo:               $9000\n"+
                                             ("----------------\n");
        
        String facturaEsperadaDescuentoCompleto = "Combo con descuento completo (Gratis)\n" +
                                                  "Precio base sin descuento:            $18000\n" +
                                                  "Descuento aplicado:                   100%\n" +
                                                  "Precio final del combo:               $0\n"+
                                                  ("----------------\n");
        
        assertEquals(facturaEsperadaSinDescuento, facturaSinDescuento, "La factura generada para el combo sin descuento no es la esperada.");
        assertEquals(facturaEsperadaConDescuento, facturaConDescuento, "La factura generada para el combo con descuento 50% no es la esperada.");
        assertEquals(facturaEsperadaDescuentoCompleto, facturaDescuentoCompleto, "La factura generada para el combo con descuento completo no es la esperada.");
    }


        
}
