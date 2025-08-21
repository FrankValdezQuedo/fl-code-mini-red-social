package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.UserListResponse;
import reactor.core.publisher.Mono;

public interface UserInputPort {
    Mono<UserListResponse> findById(Long id);
}