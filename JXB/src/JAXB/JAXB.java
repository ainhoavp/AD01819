/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAXB;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import jxb.albaran.Articulos;
import jxb.albaran.PedidoType;

/**
 *
 * @author Ainhoa
 */
public class JAXB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JAXBElement jaxbElement = null;
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File("albaran.xsd")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        PedidoType pedido = (PedidoType) jaxbElement.getValue(); //retorna el nodo raiz del xml o de la estructura del xsd.
        XMLGregorianCalendar fechaPedido = pedido.getFechaPedido();
        BigInteger eon = fechaPedido.getEon();
        long longValueEON = eon.longValue();
        BigInteger bigInteger = BigInteger.valueOf(longValueEON);
        
        String comentario = pedido.getComentario();
        pedido.setComentario(pedido.getComentario()+ " :Antiguo");
        
        Articulos articulos = pedido.getArticulos();
        List<Articulos.Articulo> listaArticulo = articulos.getArticulo();
        
        Articulos.Articulo articuloPrimero = listaArticulo.get(0);
        String codigoArticuloPrimero = articuloPrimero.getCodigo(); 
        
        Articulos.Articulo articuloSegundo = listaArticulo.get(1);
        articuloSegundo.setCodigo(codigoArticuloPrimero);
        
        
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(args.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(args, System.out);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        

    }
    
}
