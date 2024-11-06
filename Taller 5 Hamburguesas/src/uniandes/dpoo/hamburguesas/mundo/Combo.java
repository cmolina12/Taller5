package uniandes.dpoo.hamburguesas.mundo;

import java.util.ArrayList;

/**
 * La clase utilizada para organizar la información de un combo
 */
public class Combo implements Producto
{
    /**
     * Los productos que hacen parte del combo
     */
    private ArrayList<ProductoMenu> itemsCombo; 

    /**
     * El descuento que incluye el combo. Es un número entre 0 y 1, donde 0 corresponde a que no hay descuento y 1 corresponde al 100% de descuento
     */
    private double descuento;

    /**
     * El nombre del combo
     */
    private String nombreCombo;

    /**
     * Construye un nuevo combo
     * @param nombre El nombre del combo
     * @param descuento El descuento sobre el valor normal de los productos en el combo
     * @param items Los productos que hacen parte del combo
     */
    public Combo( String nombre, double descuento, ArrayList<ProductoMenu> items )
    {
        this.itemsCombo = new ArrayList<>( items );
        this.nombreCombo = nombre;
        this.descuento = descuento;
    }

    @Override
    public String getNombre( )
    {
        return nombreCombo;
    }

    /**
     * Retorna el precio del combo.
     * 
     * El precio está basado en aplicarle el descuento del combo al valor de cada uno de los productos.
     */
    @Override
    public int getPrecio( )
    {
        double precio = 0;
        for( Producto i : itemsCombo )
        {
            precio += i.getPrecio( );
        }

        return ( int ) ( precio - (precio*descuento) );
    }

    /**
     * Genera el texto que debe aparecer en la factura.
     * 
     * El texto incluye el nombre del combo, su costo y el valor del descuento
     */
    @Override
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append( "Combo " + nombreCombo + "\n" );
        sb.append( " Descuento: " + descuento + "\n" );
        sb.append( "            " + getPrecio( ) + "\n" );

        return sb.toString( );
    }

}
