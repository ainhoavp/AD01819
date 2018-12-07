package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientes.Clientes;

/**
 *
 * @author Ainhoa
 */
public class JsonToXML {

    //esta clase es una especia de milagro divino y ¡FUNCIONA!
    private JAXBElement jaxbElement = null;
    private javax.xml.bind.JAXBContext jaxbCtx = null;
    private Marshaller m = null;

    public JAXBElement unmarshalizar(File documentoXML, String nombrePaqueteDeLasClasesGeneradasPorElXML) {
        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance(nombrePaqueteDeLasClasesGeneradasPorElXML);
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new StreamSource(new java.io.File(documentoXML.toString())), Clientes.class);
            Clientes clientes = new Clientes();
            clientes = (Clientes) jaxbElement.getValue();
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex);
        }
        return jaxbElement;
    }

    public Marshaller marshalizar(JAXBElement esquema) {
        try {
            m = jaxbCtx.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(jaxbElement, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(JsonToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public JsonObjectBuilder crearDireccion(String calle, String numero, int piso, String escalera, int codigoPostal, String ciudad) {
        JsonObjectBuilder direccion = Json.createObjectBuilder()
                .add("calle", calle)
                .add("numero", numero)
                .add("piso", piso)
                .add("escalera", escalera)
                .add("cp", codigoPostal)
                .add("ciudad", ciudad);
        return direccion;
    }

    public JsonArrayBuilder crearListaDirecciones(List<JsonObjectBuilder> direcciones) {
        JsonArrayBuilder listaDirecciones = Json.createArrayBuilder();
        for (JsonObjectBuilder direccion : direcciones) {
            listaDirecciones.add(direccion);
        }
        return listaDirecciones;
    }

    public JsonArrayBuilder crearArrayApellidos(String apellido1, String apellido2) {
        JsonArrayBuilder apellidos = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("apellido1", apellido1)
                        .add("apellido2", apellido2)
                );

        return apellidos;
    }

    public JsonObject crearClienteConListaDirecciones(JsonArrayBuilder apellidos, JsonArrayBuilder direcciones, String telefono) {
        JsonObject cliente = Json.createObjectBuilder()
                .add("apellidos", Json.createArrayBuilder()
                        .add(apellidos)
                )
                .add("direccion", direcciones)
                .add("telefono", telefono)
                .build();

        return cliente;

    }

    public JsonArrayBuilder crearListaClientes(List<JsonObject> listaClientes) {
        JsonArrayBuilder clientes = Json.createArrayBuilder();
        for (JsonObject cliente : listaClientes) {
            clientes.add(cliente);
        }
        return clientes;
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
