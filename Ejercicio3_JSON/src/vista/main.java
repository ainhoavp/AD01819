/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileNotFoundException;
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
import javax.xml.bind.PropertyException;
import javax.xml.transform.stream.StreamSource;
import jaxb.clientes.Clientes;

import modelo.CrearJsonConDatos;

import modelo.CrearJson;
import modelo.CrearJsonConDatosXML;
import modelo.JsonToXML;

/**
 *
 * @author Ainhoa
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        // try {
        /*    FileWriter fichero = null;
            try {
            CrearJsonConDatos logica = new CrearJsonConDatos();
            
            JsonArrayBuilder apellidoCliente1 = logica.crearArrayApellidos("valle", "pardo");
            JsonObjectBuilder direccionCliente1 = logica.crearDireccion("prueba1", "49", 3, "a", 33207, "gijon");
            JsonObjectBuilder direccion2Cliente1 = logica.crearDireccion("prueba2", "50", 4, "b", 33208, "Oviedo");
            List<JsonObjectBuilder> listaDirecciones = new ArrayList<>();
            listaDirecciones.add(direccionCliente1);
            listaDirecciones.add(direccion2Cliente1);
            JsonArrayBuilder direccionesLista = logica.crearListaDirecciones(listaDirecciones);
            
            JsonObject cliente = logica.crearClienteConListaDirecciones(apellidoCliente1, direccionesLista, "677347479");
            //esto creó el primer cliente con dos direcciones.
            
            
            JsonArrayBuilder apellidoCliente2 = logica.crearArrayApellidos("valle2", "pardo");
            JsonObjectBuilder direccionCliente2 = logica.crearDireccion("prueba2", "49", 3, "a", 33207, "gijon2");
            JsonObjectBuilder direccion2Cliente2 = logica.crearDireccion("prueba2", "50", 4, "b", 33208, "Oviedo2");
            List<JsonObjectBuilder> listaDirecciones2 = new ArrayList<>();
            listaDirecciones2.add(direccionCliente2);
            listaDirecciones2.add(direccion2Cliente2);
            JsonArrayBuilder direccionesLista2 = logica.crearListaDirecciones(listaDirecciones2);
            JsonObject cliente2 = logica.crearClienteConListaDirecciones(apellidoCliente2, direccionesLista2, "677347479");
            //esto creó el segundo cliente con dos direcciones.
            
            
            // y como quiero crear varios clientes necesito crear un Arrray de clientes y añadirle el cliente1 y el cliente2:
            JsonArray clientes = Json.createArrayBuilder().add(cliente).add(cliente2).build();
            
            fichero = new FileWriter("pruebaAinhoa1.json");
            JsonWriter writer = Json.createWriter(fichero);
            writer.writeArray(clientes);
            fichero.flush();
            fichero.close();
            } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            try {
            fichero.close();
            } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            }*/
        //-----------------------------------------PARTE DEL XML-------------------------------------------------------
        /*           CrearJson convertir = new CrearJson();
            File documentoXML = new File("clientes.xml");
            JAXBElement unMarshal = convertir.unmarshalizar(documentoXML, "jaxb.clientes");
            
            Clientes clientes = (Clientes) unMarshal.getValue();
            
            //---------------------------------------PRIMER CLIENTE---------------------------------------------------------------
            JsonArrayBuilder apellidosCliente0 = convertir.crearArrayApellidos(clientes.getCliente().get(0).getApellido().get(0), clientes.getCliente().get(0).getApellido().get(1));
            
            JsonObjectBuilder direccion0 = convertir.crearDireccion(clientes.getCliente().get(0).getDireccion().get(0).getCalle(),
            clientes.getCliente().get(0).getDireccion().get(0).getNumero(), clientes.getCliente().get(0).getDireccion().get(0).getPiso(),
            clientes.getCliente().get(0).getDireccion().get(0).getEscalera(), clientes.getCliente().get(0).getDireccion().get(0).getCp(),
            clientes.getCliente().get(0).getDireccion().get(0).getCiudad());
            
            JsonObjectBuilder direccion1 = convertir.crearDireccion(clientes.getCliente().get(0).getDireccion().get(1).getCalle(),
            clientes.getCliente().get(0).getDireccion().get(1).getNumero(), clientes.getCliente().get(0).getDireccion().get(1).getPiso(),
            clientes.getCliente().get(0).getDireccion().get(1).getEscalera(), clientes.getCliente().get(0).getDireccion().get(1).getCp(),
            clientes.getCliente().get(0).getDireccion().get(1).getCiudad());
            
            //---------------------LISTA DIRECCIONES CLIENTE 0---------------------------------
            List<JsonObjectBuilder> listaDireccionesCliente0 = new ArrayList<>();
            listaDireccionesCliente0.add(direccion0);
            listaDireccionesCliente0.add(direccion1);
            JsonArrayBuilder arrayBuilderDeDirecciones = convertir.crearListaDirecciones(listaDireccionesCliente0);
            
            //-----------------------------------FIN---------------------------------------------------
            
            
            //--------------------------------------CLIENTE0 METIDO EN JSONOBJECT--------------------------------------------
            JsonObject cliente0 = convertir.crearClienteConListaDirecciones(apellidosCliente0, arrayBuilderDeDirecciones, "677347479");
            
            //-----------------------------------FIN---------------------------------------------------
            
            //---------------------------------------SEGUNDO CLIENTE---------------------------------------------------------------
            
            JsonArrayBuilder apellidosCliente1 = convertir.crearArrayApellidos(clientes.getCliente().get(1).getApellido().get(0), clientes.getCliente().get(1).getApellido().get(1));
            
            JsonObjectBuilder direccionCliente1primera = convertir.crearDireccion(clientes.getCliente().get(1).getDireccion().get(0).getCalle(),
            clientes.getCliente().get(1).getDireccion().get(0).getNumero(), clientes.getCliente().get(1).getDireccion().get(0).getPiso(),
            clientes.getCliente().get(1).getDireccion().get(0).getEscalera(), clientes.getCliente().get(1).getDireccion().get(0).getCp(),
            clientes.getCliente().get(1).getDireccion().get(0).getCiudad());
            
            JsonObjectBuilder direccionCliente1segunda = convertir.crearDireccion(clientes.getCliente().get(1).getDireccion().get(1).getCalle(),
            clientes.getCliente().get(1).getDireccion().get(1).getNumero(), clientes.getCliente().get(1).getDireccion().get(1).getPiso(),
            clientes.getCliente().get(1).getDireccion().get(1).getEscalera(), clientes.getCliente().get(1).getDireccion().get(1).getCp(),
            clientes.getCliente().get(1).getDireccion().get(1).getCiudad());
            
            //---------------------LISTA DIRECCIONES CLIENTE 1---------------------------------
            
            List<JsonObjectBuilder> listaDireccionesCliente1 = new ArrayList<>();
            listaDireccionesCliente1.add(direccionCliente1primera);
            listaDireccionesCliente1.add(direccionCliente1segunda);
            JsonArrayBuilder arrayBuilderDeDireccionesCliente1 = convertir.crearListaDirecciones(listaDireccionesCliente1);
            
            //-----------------------------------FIN---------------------------------------------------
            
            
            //--------------------------------------CLIENTE 1 METIDO EN JSONOBJECT--------------------------------------------
            JsonObject cliente1 = convertir.crearClienteConListaDirecciones(apellidosCliente1, arrayBuilderDeDireccionesCliente1, "699835856");
            
            //-----------------------------------FIN---------------------------------------------------
            
            
            
            //---------------------------------------TERCER CLIENTE---------------------------------------------------------------
            
            JsonArrayBuilder apellidosCliente2 = convertir.crearArrayApellidos(clientes.getCliente().get(2).getApellido().get(0), clientes.getCliente().get(2).getApellido().get(1));
            
            
            JsonObjectBuilder direccionCliente2primera = convertir.crearDireccion(clientes.getCliente().get(2).getDireccion().get(0).getCalle(),
            clientes.getCliente().get(2).getDireccion().get(0).getNumero(), clientes.getCliente().get(2).getDireccion().get(0).getPiso(),
            clientes.getCliente().get(2).getDireccion().get(0).getEscalera(), clientes.getCliente().get(2).getDireccion().get(0).getCp(),
            clientes.getCliente().get(2).getDireccion().get(0).getCiudad());
            
            JsonObjectBuilder direccionCliente2segunda = convertir.crearDireccion(clientes.getCliente().get(2).getDireccion().get(1).getCalle(),
            clientes.getCliente().get(2).getDireccion().get(1).getNumero(), clientes.getCliente().get(2).getDireccion().get(1).getPiso(),
            clientes.getCliente().get(2).getDireccion().get(1).getEscalera(), clientes.getCliente().get(2).getDireccion().get(1).getCp(),
            clientes.getCliente().get(2).getDireccion().get(1).getCiudad());
            
            //---------------------LISTA DIRECCIONES CLIENTE 1---------------------------------
            
            List<JsonObjectBuilder> listaDireccionesCliente2 = new ArrayList<>();
            listaDireccionesCliente2.add(direccionCliente2primera);
            listaDireccionesCliente2.add(direccionCliente2segunda);
            JsonArrayBuilder arrayBuilderDeDireccionesCliente2 = convertir.crearListaDirecciones(listaDireccionesCliente2);
            
            //-----------------------------------FIN---------------------------------------------------
            
            
            //--------------------------------------CLIENTE 2 METIDO EN JSONOBJECT--------------------------------------------
            JsonObject cliente2 = convertir.crearClienteConListaDirecciones(apellidosCliente2, arrayBuilderDeDireccionesCliente2, "636802970");
            
            //-----------------------------------FIN---------------------------------------------------
            
            
            //---------------------------------------CREAR LISTA DE CLIENTES--------------------------------
            List<JsonObject> listaClientes = new ArrayList<>();
            
            listaClientes.add(cliente0);
            listaClientes.add(cliente1);
            listaClientes.add(cliente2);
            
            JsonArrayBuilder arrayBuilderClientes = convertir.crearListaClientes(listaClientes);
            JsonArray todosLosClientes = arrayBuilderClientes.build();
            
            
            FileWriter fichero = null;
            
            try {
            fichero = new FileWriter("infierno.json");
            } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            JsonWriter writer = Json.createWriter(fichero);
            writer.writeArray(todosLosClientes);
            try {
            fichero.flush();
            } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichero.close();
            }   catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        //***************************DE JSON A XML**********************************
        CrearJsonConDatosXML crearJson = new CrearJsonConDatosXML();
        File file = new File("clientes.xml");
       Clientes clientes = crearJson.leerXML(file);
       
   
        
        
    }
}
