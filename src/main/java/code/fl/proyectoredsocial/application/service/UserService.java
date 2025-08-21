package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.in.UserInputPort;
import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
import code.fl.proyectoredsocial.infraestructure.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserInputPort {

    private final UserRepositoryOutputPort  userRepositoryOutputPort;

    @Override
    public Mono<UserListResponse> findById(Long id) {
        return userRepositoryOutputPort
                .findById(id)
                .map(UserUtils::convertUserSingletonResponse)
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")))
                .doOnError(error -> log.error(error.getMessage()))
                .onErrorResume(UserUtils::handleErrorUserMono);


    }

    @Override
    public Mono<UserListResponse> findAll() {
        return null;
    }

    @Override
    public Mono<UserListResponse> saveUser(UserRequest userRequest) {
        return null;
    }
}
