package uniandes.dpoo.hamburguesas.excepciones;

/**
 * Esta excepción se usa para indicar que había un pedido en curso cuando se intentó crear un nuevo pedido
 */
@SuppressWarnings("serial")
public class YaHayUnPedidoEnCursoException extends HamburguesaException
{
    /**
     * El nombre del cliente para el que ya hay un pedido en curso
     */
    private String nombreCliente;

    /**
     * El nombre del nuevo cliente para el cual se está intentando crear un nuevo pedido
     */
    private String nombreNuevoCliente;

    /**
     * Crea una nueva excepción con los nombres de los clientes involucrados
     * @param nombreCliente
     * @param nombreNuevoCliente
     */
    public YaHayUnPedidoEnCursoException( String nombreCliente, String nombreNuevoCliente )
    {
        this.nombreCliente = nombreCliente;
        this.nombreNuevoCliente = nombreNuevoCliente;
    }

    @Override
    public String getMessage( )
    {
        return "Ya existe un pedido en curso, para el cliente " + nombreCliente + " así que no se puede crear un pedido para " + nombreNuevoCliente;
    }
}
