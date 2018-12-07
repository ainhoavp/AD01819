package modelo;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author Ainhoa
 */
public class Ejercicio_3 {

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

}
