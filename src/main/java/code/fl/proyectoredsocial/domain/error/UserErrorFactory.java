package code.fl.proyectoredsocial.domain.error;

import code.fl.proyectoredsocial.infraestructure.utils.Constantes;
import io.r2dbc.spi.R2dbcException;
import lombok.NoArgsConstructor;
import reactor.core.Exceptions;

@NoArgsConstructor
public class UserErrorFactory {
    public static RuntimeException createException(Throwable error) {
        if (error instanceof R2dbcException) {
            return new ServiceUnavailableExceptions(Constantes.DATABASE_UNAVAILABLE, error);
        }
        if (Exceptions.isRetryExhausted(error)) {
            return new GatewayTimeOutExceptions(Constantes.DATABASE_TIMEOUT, error);
        }
        if (error instanceof UserNotFoundException) {
            return new UserNotFoundException(error.getMessage(), error);
        }
        return new UserExceptions(Constantes.DATABASE_USER_EXCEPTIONS, error);
    }
}


