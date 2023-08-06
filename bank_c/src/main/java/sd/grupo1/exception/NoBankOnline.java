package sd.grupo1.exception;

public class NoBankOnline extends Exception {
 // Constructor que acepta un mensaje de error
    public NoBankOnline(String mensaje) {
        super(mensaje);
    }

}