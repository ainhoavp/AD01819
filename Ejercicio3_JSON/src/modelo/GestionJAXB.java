/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente.Nombre;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author alumnop
 */
public class GestionJAXB {

    private JAXBContext jaxbCtx = null;

    public GestionJAXB(String packageName) {
        try {
            jaxbCtx = JAXBContext.newInstance(packageName);
        } catch (JAXBException ex) {
            Logger.getLogger(GestionJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public JAXBElement unMarshall(File documentoXML) {
        JAXBElement jaxbElement = null;
        try {
            Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), Clientes.class);

        } catch (JAXBException ex) {
            Logger.getLogger(GestionJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return jaxbElement;
    }

    public boolean marshall(JAXBElement jaxbElement) {
        try {
            Marshaller m = null;

            m = jaxbCtx.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(jaxbElement, System.out);
            return true;
        } catch (JAXBException ex) {
            Logger.getLogger(GestionJAXB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int totalClientes(Clientes clientes) {
        return clientes.getCliente().size();
    }

    public int totalClientesProvincia(Clientes clientes, int cp) {
        int total = 0;
        int codigoPostal = cp;
        boolean provincia = false;
        codigoPostal /= 1000;
        List<Clientes.Cliente> cliente = clientes.getCliente();

        for (Clientes.Cliente clienteLista : cliente) {
            List<TipoDireccion> direccion = clienteLista.getDireccion();
            for (TipoDireccion tipoDireccion : direccion) {
                if ((tipoDireccion.getCp() / 1000) == codigoPostal) {
                    provincia = true;
                }
            }
            if (provincia) {
                total++;
                provincia = false;
            }
        }
        return total;
    }

    public boolean borrarCliente(Clientes clientes, String primerApellido, String segundoApellido) {

        List<Clientes.Cliente> cliente = clientes.getCliente();

        boolean borrado = false;

        for (Iterator<Clientes.Cliente> ClienteIterator = cliente.iterator(); ClienteIterator.hasNext();) {
            Clientes.Cliente nextCliente = ClienteIterator.next();
            if (nextCliente.getApellido().get(0).equals(primerApellido)
                    && nextCliente.getApellido().get(1).equals(segundoApellido)) {
                ClienteIterator.remove();
                borrado = true;
            } else {
                borrado = false;
            }
        }

        return borrado;
    }

    public boolean annadirCliente(Clientes clientes, String primerApellido, String segundoApellido,
            String calle, String ciudad, int cp, String escalera, String numero, int piso, String telf, String lenguaje) {

        List<Clientes.Cliente> cliente = clientes.getCliente();

        Clientes.Cliente nuevoCliente = new Clientes.Cliente();

        nuevoCliente.getApellido().add(primerApellido);
        nuevoCliente.getApellido().add(segundoApellido);

        nuevoCliente.getDireccion().add(crearDirCliente(calle, ciudad, cp, escalera, numero, piso));

        nuevoCliente.setTelefono(telf);

        Nombre nomnreNuevoCliente = new Nombre();
        nomnreNuevoCliente.setLenguaje(lenguaje);
        nuevoCliente.setNombre(nomnreNuevoCliente);

        cliente.add(nuevoCliente);

        return true;
    }

    public boolean annadirDirACliente(Clientes clientes, String primerApellido, String segundoApellido,
            String calle, String ciudad, int cp, String escalera, String numero, int piso) {

        List<Clientes.Cliente> cliente = clientes.getCliente();
        boolean annadido = false;

        for (Iterator<Clientes.Cliente> clienteIterator = cliente.iterator(); clienteIterator.hasNext();) {
            Clientes.Cliente nextCliente = clienteIterator.next();
            if (nextCliente.getApellido().get(0).equals(primerApellido)
                    && nextCliente.getApellido().get(1).equals(segundoApellido)) {
                nextCliente.getDireccion().add(crearDirCliente(calle, ciudad, cp, escalera, numero, piso));
                annadido = true;
            } else {
                annadido = false;
            }
        }

        return annadido;
    }

    public boolean modificarDirCliente(Clientes clientes, String primerApellido, String segundoApellido, int dir,
            String calle, String ciudad, int cp, String escalera, String numero, int piso) {
        List<Clientes.Cliente> cliente = clientes.getCliente();
        boolean modificado = false;

        for (Iterator<Clientes.Cliente> clienteIterator = cliente.iterator(); clienteIterator.hasNext();) {
            Clientes.Cliente nextCliente = clienteIterator.next();
            if (nextCliente.getApellido().get(0).equals(primerApellido)
                    && nextCliente.getApellido().get(0).equals(primerApellido)) {
                nextCliente.getDireccion().get(dir).setCalle(calle);
                nextCliente.getDireccion().get(dir).setCiudad(ciudad);
                nextCliente.getDireccion().get(dir).setCp(cp);
                nextCliente.getDireccion().get(dir).setEscalera(escalera);
                nextCliente.getDireccion().get(dir).setNumero(numero);
                nextCliente.getDireccion().get(dir).setPiso(piso);
                modificado = true;
            } else {
                modificado = false;
            }
        }

        return modificado;
    }

    public boolean borrarDirsinCp(Clientes clientes) {
        List<Clientes.Cliente> cliente = clientes.getCliente();
        boolean borrado = false;

        for (Iterator<Clientes.Cliente> clienteIterator = cliente.iterator(); clienteIterator.hasNext();) {
            Clientes.Cliente nextCliente = clienteIterator.next();
            List<TipoDireccion> direccion = nextCliente.getDireccion();
            for (Iterator<TipoDireccion> dirIterator = direccion.iterator(); dirIterator.hasNext();) {
                TipoDireccion dirCliente = dirIterator.next();
                if (dirCliente.getCp() == 0) {
                    dirIterator.remove();
                    borrado = true;
                }
            }
        }
        return borrado;
    }

    public TipoDireccion crearDirCliente(String calle, String ciudad, int cp, String escalera, String numero, int piso) {
        TipoDireccion dirNuevoCliente = new TipoDireccion();

        dirNuevoCliente.setCalle(calle);
        dirNuevoCliente.setCiudad(ciudad);
        dirNuevoCliente.setCp(cp);
        dirNuevoCliente.setEscalera(escalera);
        dirNuevoCliente.setNumero(numero);
        dirNuevoCliente.setPiso(piso);

        return dirNuevoCliente;
    }

    public boolean annadirClienteDirs(Clientes clientes, String primerApellido, String segundoApellido,
            List<TipoDireccion> listaDirs, String telf, String lenguaje) {

        List<Clientes.Cliente> cliente = clientes.getCliente();

        Clientes.Cliente nuevoCliente = new Clientes.Cliente();

        nuevoCliente.getApellido().add(primerApellido);
        nuevoCliente.getApellido().add(segundoApellido);

        for (TipoDireccion datoDir : listaDirs) {
            nuevoCliente.getDireccion().add(crearDirCliente(datoDir.getCalle(), datoDir.getCiudad(),
                     datoDir.getCp(), datoDir.getEscalera(), datoDir.getNumero(), datoDir.getPiso()));
        }

        nuevoCliente.setTelefono(telf);

        Nombre nomnreNuevoCliente = new Nombre();
        nomnreNuevoCliente.setLenguaje(lenguaje);
        nuevoCliente.setNombre(nomnreNuevoCliente);

        cliente.add(nuevoCliente);

        return true;

    }

}
