package ejercicios1JAXB;

import Excepciones.MisExcepciones;
import generated.Articulos;
import generated.Direccion;
import generated.PedidoType;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Ainhoa
 */
public class Logica_Ejercicio1_JAXB implements ConvertirXSD_Operaciones {

    javax.xml.bind.JAXBContext jaxbCtx = null;

    /**
     *
     * @param paquete
     * @param nombreXML
     * @return
     */
    @Override
    public JAXBElement unMarshalizar(String paquete, String nombreXML) {
        JAXBElement jaxbElement = null;
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(paquete); //"jaxb.albaran"
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File(nombreXML)); //NOI18N //"albaran.xml"
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
     * @throws MisExcepciones.ElArticuloYaExiste
     */
    @Override
    public Articulos.Articulo aniadirArticulo(PedidoType pedidoType, String nombreProducto, int cantidad, int dia, int mes, int anio, BigDecimal precio, String comentario, XMLGregorianCalendar fechaEnvio, String codigo) throws Excepciones.MisExcepciones.ElArticuloYaExiste, Excepciones.MisExcepciones.FechaInvalida {

        Articulos.Articulo nuevoArticulo = new Articulos.Articulo();
        if (anio < 1988 && anio > 2030 || mes < 1 && mes > 12 || dia < 1 && dia > 31) {
            throw new MisExcepciones.FechaInvalida("La fecha introducida no es correcta.");
        } else {
            fechaEnvio.setDay(dia);
            fechaEnvio.setMonth(mes);
            fechaEnvio.setYear(anio);

            nuevoArticulo.setNombreProducto(nombreProducto);
            nuevoArticulo.setCantidad(cantidad);
            nuevoArticulo.setPrecio(precio);
            nuevoArticulo.setComentario(comentario);
            nuevoArticulo.setFechaEnvio(fechaEnvio);
            nuevoArticulo.setCodigo(codigo);

            if (pedidoType.getArticulos().getArticulo().contains(nuevoArticulo)) {
                throw new MisExcepciones.ElArticuloYaExiste("El artículo ya existe");

            } else {
                pedidoType.getArticulos().getArticulo().add(nuevoArticulo);
            }
        }
        return nuevoArticulo;

    }

    @Override
    public Direccion modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, BigDecimal codigoPostal, String pais) throws Excepciones.MisExcepciones.LaDireccionYaExiste {
        PedidoType pedidoTipo = pedidoType;
        Direccion nuevaDireccion = new Direccion();

        nuevaDireccion.setNombre(nombre);
        nuevaDireccion.setCalle(calle);
        nuevaDireccion.setCiudad(ciudad);
        nuevaDireccion.setProvincia(provincia);
        nuevaDireccion.setCodigoPostal(codigoPostal);
        nuevaDireccion.setPais(pais);

        if (pedidoTipo.getFacturarA().getNombre().equalsIgnoreCase(nombre) && pedidoTipo.getFacturarA().getCalle().equalsIgnoreCase(calle)) {
            throw new MisExcepciones.LaDireccionYaExiste("La dirección nueva es la misma que la actual.");
        } else {

            pedidoTipo.setFacturarA(nuevaDireccion);
        }

        return nuevaDireccion;
    }

    @Override
    public double calcularPrecioArticulo(PedidoType pedidoType) throws Excepciones.MisExcepciones.NoHayPedidos {
        double precioPedido = 0;
        if (pedidoType.getArticulos().getArticulo() != null) {
            List<Articulos.Articulo> listaArticulosPedido = pedidoType.getArticulos().getArticulo();

            for (Articulos.Articulo articulo : listaArticulosPedido) {
                precioPedido += articulo.getPrecio().doubleValue() * articulo.getCantidad();
            }
        } else {
            throw new MisExcepciones.NoHayPedidos("No hay articulos en este pedido");
        }

        return precioPedido;

    }

    @Override
    public boolean borrarArticulo(PedidoType pedidoType, String nombre) throws Excepciones.MisExcepciones.NoHayProductoQueBorrar {

        for (Iterator<Articulos.Articulo> iterator = pedidoType.getArticulos().getArticulo().iterator(); iterator.hasNext();) {
            Articulos.Articulo next = iterator.next();
            if (next.getNombreProducto().equalsIgnoreCase(nombre)) {
                iterator.remove();
                return true;
            } else {
                throw new MisExcepciones.NoHayProductoQueBorrar("No se pudo borra, la lista de articulos no contiene el nombre del articulo");
            }
        }
        return false;
    }

}
