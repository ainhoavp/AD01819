/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/*
import jaxb.clientes.Articulos;
import jaxb.albaran.Direccion;
import jaxb.albaran.PedidoType;
import java.math.BigDecimal;*/
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Ainhoa
 */
public interface ConvertirXSD_Operaciones {
    
    public abstract JAXBElement unMarshalizar(String paquete, String nombreXML);
   
    
    public void marshaller(JAXBElement jaxbElement);
    
    /*    public Articulos.Articulo aniadirArticulo(PedidoType pedidoType, String nombreProducto, int cantidad, BigDecimal precio, String comentario, XMLGregorianCalendar fechaEnvio, String codigo) throws excepciones.MisExcepciones.ElArticuloYaExiste, excepciones.MisExcepciones.FechaInvalida;
    
    public Direccion modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, BigDecimal codigoPostal, String pais) throws excepciones.MisExcepciones.LaDireccionYaExiste;
    
    public double calcularPrecioArticulo(PedidoType pedidoType) throws excepciones.MisExcepciones.NoHayPedidos;
    
    public boolean borrarArticulo(PedidoType pedidoType, String nombre) throws excepciones.MisExcepciones.NoHayProductoQueBorrar;*/
}
