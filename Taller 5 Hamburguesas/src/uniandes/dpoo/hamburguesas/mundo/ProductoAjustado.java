package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;

/**
 * Un producto ajustado es un producto para el cual el cliente solicitó alguna modificación.
 */
public class ProductoAjustado implements Producto
{
    /**
     * El producto base que el cliente sobre el cual el cliente quiere hacer ajustes
     */
    private ProductoMenu productoBase;

    /**
     * La lista de ingrediente que el usuario quiere agregar. El mismo ingrediente puede aparecer varias veces.
     */
    private ArrayList<Ingrediente> agregados;

    /**
     * La lista de ingrediente que el usuario quiere eliminar.
     */
    private ArrayList<Ingrediente> eliminados;

    /**
     * Construye un nuevo producto ajustado a partir del producto base y sin modificaciones
     * @param productoBase El producto base que se va a ajustar
     */
    public ProductoAjustado( ProductoMenu productoBase )
    {
        this.productoBase = productoBase;
        agregados = new ArrayList<Ingrediente>( );
        eliminados = new ArrayList<Ingrediente>( );
    }

    @Override
    public String getNombre( )
    {
        return productoBase.getNombre( );
    }

    public void agregarIngrediente( Ingrediente ingrediente )
    {
        agregados.add( ingrediente );
    }

    public void eliminarIngrediente( Ingrediente ingrediente )
    {
        
    	eliminados.add( ingrediente );
    
    }



    /**
     * Retorna el precio del producto ajustado, que debe ser igual al del producto base, sumándole el precio de los ingredientes adicionales.
     */
    @Override
    public int getPrecio()
    {
        int precio = productoBase.getPrecio( );

        ArrayList<Ingrediente> ingredientesFinales = new ArrayList<>(agregados);
        
        // Remover ingredietnes en eliminados de la lista de agregados para calcular el precio final
        
        for (Ingrediente ingEliminado : eliminados) {
        	ingredientesFinales.remove(ingEliminado);        
        	}
        
        
        // Sumar el costo de los ingredientes finales al precio base
        
        for (Ingrediente ing : ingredientesFinales) {
        	precio += ing.getCostoAdicional();
        }
        
        return precio;
      

        
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el producto base, los ingredientes adicionales con su costo, los ingredientes eliminados, y el precio total
     */
    @Override
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append(productoBase.getNombre()); // Nombre del producto base
        sb.append("            " + productoBase.getPrecio( ) + "\n"); // Mostrar el precio del producto base
       
        
        
        for( Ingrediente ing : agregados )
        {
            sb.append( "    +" + ing.getNombre( ) );
            sb.append( "                " + ing.getCostoAdicional( ) );
        }
        for( Ingrediente ing : eliminados )
        {
            sb.append( "    -" + ing.getNombre( ) );
        }
        
        // Crear la lista de ingredientes que afectan el precio
        ArrayList<Ingrediente> ingredientesFinales = new ArrayList<>(agregados);
        for (Ingrediente ingEliminado : eliminados) {
            ingredientesFinales.remove(ingEliminado);
        }

        // Mostrar los ingredientes finales (que afectan el precio) con su costo
        for (Ingrediente ing : ingredientesFinales) {
            sb.append("    +").append(ing.getNombre());
            sb.append("                ").append(ing.getCostoAdicional()).append("\n");
        }

        sb.append( "            " + getPrecio( ) + "\n" );

        // Linea de depuracion
        System.out.println(sb.toString());
        
        return sb.toString( );
        
    }

}
