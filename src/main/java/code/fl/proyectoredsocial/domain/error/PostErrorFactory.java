package code.fl.proyectoredsocial.domain.error;

import code.fl.proyectoredsocial.infraestructure.utils.Constantes;
import io.r2dbc.spi.R2dbcException;
import reactor.core.Exceptions;

public class PostErrorFactory {
    public static RuntimeException createException(Throwable error) {
        if (error instanceof R2dbcException) {
            return new ServiceUnavailableExceptions(Constantes.DATABASE_UNAVAILABLE, error);
        }
        if (Exceptions.isRetryExhausted(error)) {
            return new GatewayTimeOutExceptions(Constantes.DATABASE_TIMEOUT, error);
        }
        if (error instanceof UserNotFoundException) {
            return new PostNotFoundException(error.getMessage(), error);
        }
        return new PostExceptions(Constantes.DATABASE_USER_EXCEPTIONS, error);
    }
}
