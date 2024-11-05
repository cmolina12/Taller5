package uniandes.dpoo.hamburguesas.excepciones;

/**
 * Esta excepción se usa para indicar que un producto que requiere un combo no aparece en la información del restaurante
 */
@SuppressWarnings("serial")
public class ProductoFaltanteException extends HamburguesaException
{
    /**
     * El nombre del producto faltante
     */
    private String nombreProducto;

    /**
     * Construye la excepción registrando el nombre del producto
     * @param nombreProducto El producto faltante
     */
    public ProductoFaltanteException( String nombreProducto )
    {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String getMessage( )
    {
        return "El producto " + nombreProducto + " no aparece en la información del restaurante";
    }
}
