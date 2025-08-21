package code.fl.proyectoredsocial.domain.error;

public class GatewayTimeOutExceptions extends RuntimeException {
    public GatewayTimeOutExceptions(String message, Throwable cause) {
        super(message,cause );
    }
}
