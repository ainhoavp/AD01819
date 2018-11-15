/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios1JAXB;

import generated.Articulos;
import generated.PedidoType;
import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Ainhoa
 */
public interface ConvertirXSD_Operaciones {
    
    public abstract JAXBElement unMarshalizar();
    
    public void marshaller();
    
    public Articulos.Articulo aniadirArticulo(PedidoType pedidoType, String nombreProducto,int cantidad, BigDecimal precio, String comentario, XMLGregorianCalendar fechaEnvio, String codigo);    
    
    public void modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, BigDecimal codigoPostal, String pais);
    
}
