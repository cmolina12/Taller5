package uniandes.dpoo.hamburguesas.mundo;

/**
 * Esta clase se utiliza para mantener la información de un ingrediente que se puede agregar o eliminar de un producto
 */
public class Ingrediente
{
    /**
     * El nombre del ingrediente
     */
    private String nombre;

    /**
     * El costo de agregar el ingrediente a un producto
     */
    private int costoAdicional;

    /**
     * Construye un nuevo ingrediente con un nombre y un costo adicional
     * @param nombre
     * @param costoAdicional
     */
    public Ingrediente( String nombre, int costoAdicional )
    {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    /**
     * Retorna el nombre del ingrediente
     * @return
     */
    public String getNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el costo adicional del ingrediente
     * @return
     */
    public int getCostoAdicional( )
    {
        return costoAdicional;
    }

}
