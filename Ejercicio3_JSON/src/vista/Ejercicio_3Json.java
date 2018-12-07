package vista;


import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ainhoa
 */
public class Ejercicio_3Json {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileWriter fichero = null;
        try {
            //1)	Crea un fichero JSON (jsonWriter) con la estructura del XSD
            //que añade un cliente con dos direcciones.
            
            JsonObject cliente = Json.createObjectBuilder()
                    .add("apellidos", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("apellido1", "Garcia")
                                    .add("apellido2", "Gonzalez")
                            )
                    )
                    .add("direccion", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("calle", "Schulz")
                                    .add("numero", "49")
                                    .add("piso", 49)
                                    .add("escalera", "a")
                                    .add("codigoPostal", 33208)
                                    .add("ciudad", "Gijon")
                            )
                            .add(Json.createObjectBuilder()
                                    .add("calle", "San Jose")
                                    .add("numero", "2")
                                    .add("piso", 6)
                                    .add("escalera", "b")
                                    .add("codigoPostal", 33209)
                                    .add("ciudad", "Gijon")
                            )
                    )
                    .add("telefono", "677347479")
                    .build();
          
            
            fichero = new FileWriter("ainhoa.json");
            JsonWriter writer = Json.createWriter(fichero);
            writer.writeObject(cliente);
            fichero.flush();
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio_3Json.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio_3Json.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

}
