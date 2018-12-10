package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.xml.bind.JAXBElement;
import jaxb.clientes.Clientes;
import jaxb.clientes.Clientes.Cliente;
import jaxb.clientes.TipoDireccion;

/**
 *
 * @author Ainhoa
 */
public class JsonToXML {

    //*1º binding del xsd para tener mapeadas las clases.
    //Clientes clientes = new Clientes();
    //*2º Leer el Json:***********************************************************
    //*3º "Extraer la información del Json para poder "meterla" en las clases correspondientes para el XML
    public void JsonToXML(String documentoJson, String paquete, String xml) throws FileNotFoundException {

        //-1º creamos el array donde quiero guardar los datos del Json:
        JsonReader reader;
        JsonArray arrayJsonLeido = null;

        //1º leemos el json:
        FileReader archivoAleer = new FileReader(documentoJson);
        reader = Json.createReader(archivoAleer);
        arrayJsonLeido = reader.readArray();

        //2º unmarhalizamos el xml para ello creare una instancia de la clase donde tengo el metodo de unmarshalizar
        CrearJson metodosJson = new CrearJson();
        File documentoXML = new File(xml);
        GestionJAXB gestion = new GestionJAXB(paquete);

        JAXBElement unmarshal = metodosJson.unmarshalizar(documentoXML, paquete);

        //3º pedimos el getValue que devuelve devuelve el modelo de contenido y los valores de atributo para este elementoy lo casteamos a Clientes
        Clientes clientes = (Clientes) unmarshal.getValue();

        /*ahora en el objeto clientes ya tenemos el "mapa" de las clases del xml desde el elemento raiz, es decir ese objeto contiene ya una lista de clientes.
       por lo tanto solo hay que trabajar con ella teniendo el cuenta la posicion de los elementos */
        //creo las listas y objetos necesarios para guardar la informacion.
        //recorro el array que contiene la informacion del json
        for (Iterator<JsonValue> iterator = arrayJsonLeido.iterator(); iterator.hasNext();) {
            List<TipoDireccion> listaDirecciones = new ArrayList<>();
            JsonObject cliente = (JsonObject) iterator.next();

            //le digo que en la lista dirección me meta la lista que hay dentro del arrayJsonLeido que se llama o tiene de clave "direccion"
            JsonArray direccion = cliente.getJsonArray("direccion");
            if (direccion.size() > 0) {
                //si en la lista de direcciones hay más de una:
                for (Iterator<JsonValue> iteratorDire = direccion.iterator(); iteratorDire.hasNext();) {
                    //recorro la lista y para cada elemento de la lista direcciones (que es una dirección) creo un JsonObject:
                    JsonObject cadaDireccion = (JsonObject) iteratorDire.next();
                    /*y en la lista que cree anteriormente de tipo <TipoDireccion> con los metodos de la gestion de jaxb creo la dirección o direcciones que luego
                    añadiré al cliente. El metodo crearDirCliente devuelve una lista de tipo <TipoDireccion> y lo que hago es que para cada dirección le pido los
                    datos con el nombre clave "calle", "ciudad"....*/
                    listaDirecciones.add(gestion.crearDirCliente(cadaDireccion.getString("calle"),
                            cadaDireccion.getString("ciudad"), cadaDireccion.getInt("cp"),
                            cadaDireccion.getString("escalera"), cadaDireccion.getString("numero"), cadaDireccion.getInt("piso")));
                }
            }

            gestion.annadirClienteDirs(clientes, cliente.getString("apellido1"),
                    cliente.getString("apellido2"), listaDirecciones, cliente.getString("telefono"), "");
        }

        metodosJson.marshalizar(unmarshal);

    }

}
