package code.fl.proyectoredsocial.infraestructure.adapter;

import code.fl.proyectoredsocial.domain.error.UserNotFoundException;
import code.fl.proyectoredsocial.domain.model.ErrorResponse;
import code.fl.proyectoredsocial.domain.model.Notification;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public Mono<UserListResponse> handleUserNotFound(UserNotFoundException ex, ServerWebExchange exchange) {
        Notification notification = Notification.builder()
                .category("USER")
                .code("USER_NOT_FOUND")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .severity("ERROR")
                .action("Verificar el ID del usuario")
                .build();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setNotifications(List.of(notification));

        UserListResponse response = new UserListResponse();
        response.setData(null);
        response.setErrorResponse(errorResponse);

        return Mono.just(response);
    }


    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<String>> handleOtherExceptions(Exception ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error inesperado: " + ex.getMessage()));
    }
}
