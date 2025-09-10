package code.fl.proyectoredsocial.domain.error;

public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(String message) {
        super(message);
    }

    public ComentarioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

