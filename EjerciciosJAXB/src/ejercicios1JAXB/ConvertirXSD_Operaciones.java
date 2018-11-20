/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicios1JAXB;

import generated.Articulos;
import generated.Direccion;
import generated.PedidoType;
import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Ainhoa
 */
public interface ConvertirXSD_Operaciones {
    
    public abstract JAXBElement unMarshalizar(String paquete, String nombreXML);
    
    public void marshaller();
    
    /**
     *
     * @param pedidoType
     * @param nombreProducto
     * @param cantidad
     * @param dia
     * @param mes
     * @param anio
     * @param precio
     * @param comentario
     * @param fechaEnvio
     * @param codigo
     * @return
     * @throws MisExcepciones.elArticuloYaExiste
     */
    public Articulos.Articulo aniadirArticulo(PedidoType pedidoType, String nombreProducto,int cantidad, int dia, int mes, int anio, BigDecimal precio, String comentario, XMLGregorianCalendar fechaEnvio, String codigo) throws Excepciones.MisExcepciones.ElArticuloYaExiste, Excepciones.MisExcepciones.FechaInvalida;
    
    public Direccion modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, BigDecimal codigoPostal, String pais) throws Excepciones.MisExcepciones.LaDireccionYaExiste;
    
    public double calcularPrecioArticulo(PedidoType pedidoType) throws Excepciones.MisExcepciones.NoHayPedidos;
    
    public boolean borrarArticulo(PedidoType pedidoType, String nombre) throws Excepciones.MisExcepciones.NoHayProductoQueBorrar;
}
