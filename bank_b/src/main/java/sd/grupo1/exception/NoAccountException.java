package sd.grupo1.exception;

public class NoAccountException extends Exception {
 // Constructor que acepta un mensaje de error
    public NoAccountException(String mensaje) {
        super(mensaje);
    }

}