package uniandes.dpoo.hamburguesas.excepciones;

/**
 * La excepción que se usa para indicar que no se puede realizar una acción en el restaurante porque no hay un pedido en curso
 */
@SuppressWarnings("serial")
public class NoHayPedidoEnCursoException extends HamburguesaException
{
    @Override
    public String getMessage( )
    {
        return "Actualmente no hay un pedido en curso";
    }
}
