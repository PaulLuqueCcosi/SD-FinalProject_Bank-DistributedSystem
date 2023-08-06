package sd.grupo1.exception;

public class NoUserException extends Exception {
 // Constructor que acepta un mensaje de error
    public NoUserException(String mensaje) {
        super(mensaje);
    }

}