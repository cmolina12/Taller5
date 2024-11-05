package uniandes.dpoo.hamburguesas.excepciones;

/**
 * Esta excepción se usa para indicar que un ingreditente está repetido en el archivo de información del restaurante
 */
@SuppressWarnings("serial")
public class IngredienteRepetidoException extends HamburguesaException
{
    /**
     * El ingrediente repetido
     */
    private String nombreIngrediente;

    /**
     * Construye la excepción registrando el nombre del ingrediente
     * 
     * @param nombreIngrediente El ingrediente repetido
     */
    public IngredienteRepetidoException( String nombreIngrediente )
    {
        this.nombreIngrediente = nombreIngrediente;
    }

    @Override
    public String getMessage( )
    {
        return "El ingrediente " + nombreIngrediente + " está repetido";
    }
}
