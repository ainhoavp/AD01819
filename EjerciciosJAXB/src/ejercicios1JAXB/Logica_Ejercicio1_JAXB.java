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
public class Logica_Ejercicio1_JAXB implements ConvertirXSD_Operaciones {

   
    javax.xml.bind.JAXBContext jaxbCtx = null;

    @Override
    public JAXBElement unMarshalizar() {
        JAXBElement jaxbElement = null;
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File("albaran.xml")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

        return jaxbElement;

    }

    @Override
    public void marshaller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Articulos.Articulo aniadirArticulo(PedidoType pedidoType, String nombreProducto,int cantidad, BigDecimal precio, String comentario, XMLGregorianCalendar fechaEnvio, String codigo) {
        
        Articulos.Articulo nuevoArticulo = new Articulos.Articulo();
        
        nuevoArticulo.setNombreProducto(nombreProducto);
        nuevoArticulo.setCantidad(cantidad);
        nuevoArticulo.setPrecio(precio);
        nuevoArticulo.setComentario(comentario);
        nuevoArticulo.setFechaEnvio(fechaEnvio);
        nuevoArticulo.setCodigo(codigo);
        
        if(pedidoType.getArticulos().getArticulo().contains(nuevoArticulo)){
            System.out.println("El articulo ya existe");
        }else{
        pedidoType.getArticulos().getArticulo().add(nuevoArticulo);
        }
        
        
        return nuevoArticulo;

    }

    @Override
    public void modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, BigDecimal codigoPostal, String pais) {
//hacerlo boolean si se pudo o no añadir.
        PedidoType pedidoTipo = pedidoType;
        
        pedidoTipo.getFacturarA().setNombre(nombre);
        pedidoTipo.getFacturarA().setCalle(calle);
        pedidoTipo.getFacturarA().setCiudad(ciudad);
        pedidoTipo.getFacturarA().setProvincia(provincia);
        pedidoTipo.getFacturarA().setCodigoPostal(codigoPostal);
        pedidoTipo.getFacturarA().setPais(pais);
        
        
    }


    
    
    

}
