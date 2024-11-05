package uniandes.dpoo.hamburguesas.excepciones;

/**
 * Esta excepción se usa para indicar que un producto está repetido en el archivo de información del restaurante
 */
@SuppressWarnings("serial")
public class ProductoRepetidoException extends HamburguesaException
{
    /**
     * El nombre del producto repetido
     */
    private String nombreProducto;

    /**
     * Construye la excepción registrando el nombre del producto
     * @param nombreProducto El producto repetido
     */
    public ProductoRepetidoException( String nombreProducto )
    {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public String getMessage( )
    {
        return "El producto " + nombreProducto + " está repetido";
    }
}
