package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.json.*;
import javax.xml.bind.JAXBElement;
import jaxb.clientes.Clientes;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Ainhoa
 */
public class CrearJsonConDatosXML {

    CrearJsonConDatos crearJson = new CrearJsonConDatos();
    GestionJAXB gestionJAXB = new GestionJAXB("clientes.xml");
    Clientes clientes = null;

    public Clientes leerXML(File file) {
        JAXBElement jaxbElement = gestionJAXB.unMarshall(file);
        clientes = (Clientes) jaxbElement.getValue();
        return clientes;
    }

    public JsonArrayBuilder crearApellidos(Clientes.Cliente cliente) {
        JsonArrayBuilder apellidosJArrayBuilder = crearJson.crearArrayApellidos(cliente.getApellido().get(0), cliente.getApellido().get(1));
        return apellidosJArrayBuilder;

    }

    public JsonArrayBuilder crearDireccion(Clientes.Cliente cliente) {

        JsonArrayBuilder listaDireccionesJsonArrayBuilder = null;
        List<TipoDireccion> listaDirecciones = cliente.getDireccion();
        for (TipoDireccion direccion : listaDirecciones) {
            listaDireccionesJsonArrayBuilder.add(crearJson.crearDireccion(direccion.getCalle(), direccion.getNumero(), direccion.getPiso(), direccion.getEscalera(),
                    direccion.getCp(), direccion.getCiudad()));

        }
        return listaDireccionesJsonArrayBuilder;

    }
    
   /**
    * Crea un JSONObject Cliente a partir de un Objeto Cliente
    * @param cliente
    * @return 
    */     
    public JsonObject crearCliente(Clientes.Cliente cliente){
        JsonObject clienteCreado = crearJson.crearClienteConListaDirecciones(crearApellidos(cliente), crearDireccion(cliente), cliente.getTelefono());
        return clienteCreado;
    }
    
    /**
     * Crea un JsonArrayBuilder Clientes  a partir de un Objeto Clientes
     * @param listaClientes
     * @return 
     */
    public JsonArrayBuilder crearListaClientes(Clientes clientes){
        JsonArrayBuilder arrayDeClientesJsonArrayBuilder = null;
JsonObject clienteJsonObject = null;
        for (Clientes.Cliente cliente : clientes.getCliente()) {
           clienteJsonObject = crearCliente(cliente);
           arrayDeClientesJsonArrayBuilder.add(clienteJsonObject);
        }
        return arrayDeClientesJsonArrayBuilder;
        
    }
    
    
    
      public void crearFicheroJSONconArrayBuider(String rutaFichero, JsonArrayBuilder clientes) throws IOException {

        JsonArray arrayJson = clientes
                .build();
        FileWriter ficheroSalida = new FileWriter(rutaFichero);
        JsonWriter jsonWriter = Json.createWriter(ficheroSalida);
        jsonWriter.writeArray(arrayJson);
        ficheroSalida.flush();
        ficheroSalida.close();

    }

    
    
    
    
}
