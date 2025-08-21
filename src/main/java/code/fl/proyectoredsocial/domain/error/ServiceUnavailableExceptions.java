package code.fl.proyectoredsocial.domain.error;

public class ServiceUnavailableExceptions extends RuntimeException {
    public ServiceUnavailableExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
