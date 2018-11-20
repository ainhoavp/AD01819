package Excepciones;

/**
 *
 * @author Ainhoa
 */
public class MisExcepciones {
    
    
    public static class ElArticuloYaExiste extends Exception{
        
        public ElArticuloYaExiste(String mensaje){
            super(mensaje);
        }
        
    }
    
    public static class LaDireccionYaExiste extends Exception{
        public LaDireccionYaExiste(String mensaje){
            super(mensaje);
        }
    }
    
    public static class NoHayPedidos extends Exception{
        public NoHayPedidos(String mensaje){
            super(mensaje);
        }
    }
    
    public static class NoHayProductoQueBorrar extends Exception{
        public NoHayProductoQueBorrar(String mensaje){
            super(mensaje);
        }
    }
    
    public static class FechaInvalida extends Exception{
        public FechaInvalida(String mensaje){
            super(mensaje);
        }
    }
    
    
}
