package uniandes.dpoo.hamburguesas.mundo;

/**
 * Esta interfaz se utiliza para todos los elementos que pueden hacer parte de un pedido
 */
public interface Producto
{
    /**
     * Retorna el precio que aparecerá en la factura del pedido
     * @return
     */
    public int getPrecio( );

    /**
     * Retorna el nombre del producto
     * @return
     */
    public String getNombre( );

    /**
     * Retorna el texto que describe al producto y que debe aparecer en la factura.
     * 
     * La estructura del texto dependerá del tipo de producto
     * @return Un texto que incluye al menos el nombre del producto y el precio
     */
    public String generarTextoFactura( );
}
