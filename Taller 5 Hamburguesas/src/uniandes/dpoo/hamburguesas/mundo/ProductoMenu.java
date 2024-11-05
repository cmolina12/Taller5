package uniandes.dpoo.hamburguesas.mundo;

/**
 * Esta es la clase que se utiliza para representar un producto que ofrece el restaurante y que puede hacer parte de un combo
 */
public class ProductoMenu implements Producto
{
    /**
     * El nombre del producto
     */
    private String nombre;

    /**
     * El precio base del producto, antes de que se le agreguen ingredientes adicionales
     */
    private int precioBase;

    /**
     * Crea un nuevo producto con un nombre y un precio base
     * @param nombre
     * @param precioBase
     */
    public ProductoMenu( String nombre, int precioBase )
    {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    @Override
    public String getNombre( )
    {
        return nombre;
    }

    @Override
    public int getPrecio( )
    {
        return precioBase;
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el nombre del producto y su costo
     */
    @Override
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append( nombre + "\n" );
        sb.append( "            " + getPrecio( ) + "\n" );

        return sb.toString( );
    }

}