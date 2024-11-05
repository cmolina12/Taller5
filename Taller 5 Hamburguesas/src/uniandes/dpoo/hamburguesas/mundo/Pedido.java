package uniandes.dpoo.hamburguesas.mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * La clase para organizar la información de un pedido
 */
public class Pedido
{
    /**
     * El porcentaje del IVA como un número entre 0 y 1
     */
    private static final double IVA = 0.19;

    /**
     * El número de pedidos que se han creado hasta el momento
     */
    private static int numeroPedidos;

    /**
     * El número identificador de un pedido
     */
    private int idPedido;

    /**
     * Los productos que hacen parte del pedido
     */
    private ArrayList<Producto> productos;

    /**
     * El nombre del cliente que hizo el pedido
     */
    private String nombreCliente;

    /**
     * La dirección del cliente
     */
    private String direccionCliente;

    /**
     * Crea un nuevo pedido, sólo con los datos del cliente.
     * 
     * Al crear un nuevo pedido se le debe asignar como identificador el número de pedido, y se debe incrementar el valor del atributo estático 'numeroPedidos'
     * @param nombreCliente
     * @param direccionCliente
     */
    public Pedido( String nombreCliente, String direccionCliente )
    {
        this.idPedido = numeroPedidos++;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        productos = new ArrayList<Producto>( );
    }

    /**
     * Retorna el identificador del pedido
     * @return
     */
    public int getIdPedido( )
    {
        return idPedido;
    }

    /**
     * Retorna el nombre del cliente
     * @return
     */
    public String getNombreCliente( )
    {
        return nombreCliente;
    }

    /**
     * Agrega un nuevo producto al pedido
     * @param nuevoProducto El producto que debe agregarse al pedido
     */
    public void agregarProducto( Producto nuevoProducto )
    {
        productos.add( nuevoProducto );
    }

    /**
     * Retorna el precio total del pedido, basado en el valor de cada uno de los productos y en el IVA
     * @return La sumatoria de los precios de los productos con el valor adicional del IVA
     */
    public int getPrecioTotalPedido( )
    {
        return getPrecioNetoPedido( ) + getPrecioIVAPedido( );
    }

    /**
     * Retorna el precio de los productos del pedido
     * @return La sumatoria de los precios de los productos
     */
    private int getPrecioNetoPedido( )
    {
        int valor = 0;
        for( Producto item : productos )
        {
            valor += item.getPrecio( );
        }
        return valor;
    }

    /**
     * Retorna el valor del IVA del producto, que corresponde al 19% del precio neto
     * @return
     */
    private int getPrecioIVAPedido( )
    {
        return ( int ) ( getPrecioNetoPedido( ) * IVA );
    }

    /**
     * Retorna una cadena con toda la información que debe quedar en el factura. La información incluye:
     * 
     * 1. El nombre y la dirección del cliente
     * 
     * 2. La información de cada uno de los productos
     * 
     * 3. El precio neto del pedido
     * 
     * 4. El IVA del pedido
     * 
     * 5. El valor total del pedido
     * @return
     */
    public String generarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );

        sb.append( "Cliente: " + nombreCliente + "\n" );
        sb.append( "Dirección: " + direccionCliente + "\n" );
        sb.append( "----------------\n" );

        for( Producto item : productos )
        {
            sb.append( item.generarTextoFactura( ) );
        }

        sb.append( "----------------\n" );
        sb.append( "Precio Neto:  " + getPrecioNetoPedido( ) + "\n" );
        sb.append( "IVA:          " + getPrecioIVAPedido( ) + "\n" );
        sb.append( "Precio Total: " + getPrecioTotalPedido( ) + "\n" );

        return sb.toString( );
    }

    /**
     * Guarda la factura del pedido en el archivo indicado
     * @param archivo El archivo donde debe quedar almacenada la factua del pedido
     * @throws FileNotFoundException Se lanza esta excepción si no se puede crear el archivo para guardar la factura
     */
    public void guardarFactura( File archivo ) throws FileNotFoundException
    {
        String factura = generarTextoFactura( );

        PrintWriter out;
        out = new PrintWriter( archivo );
        out.print( factura );
        out.close( );
    }

}
