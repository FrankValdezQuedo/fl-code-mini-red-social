package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.in.UserInputPort;
import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.error.UserNotFoundException;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.domain.model.UserResponse;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
import code.fl.proyectoredsocial.infraestructure.utils.Constantes;
import code.fl.proyectoredsocial.infraestructure.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserInputPort {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    @Override
    public Mono<UserListResponse> findById(Long id) {
        return userRepositoryOutputPort
                .findById(id)
                .map(UserUtils::convertUserSingletonResponse)
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario con id " + id + " no encontrado")))
                .doOnError(error -> log.error("Error en findById({}): {}", id, error.getMessage()))
                .onErrorResume(UserUtils::handleErrorUserMono);
    }

    @Override
    public Mono<UserListResponse> findAll() {
        return userRepositoryOutputPort
                .findAll()
                .collectList()
                .map(UserUtils::convertCustomerListResponse)
                .doOnError(error -> log.error("Error en findAll(): {}", error.getMessage()))
                .onErrorResume(UserUtils::handleErrorUserMono);

    }

    @Override
    public Mono<UserResponse> saveUser(UserRequest userRequest) {
        return Mono.just(userRequest)
                .map(user -> UserUtils.convertUserEntity(userRequest))
                .flatMap(userRepositoryOutputPort::saveUserOrUpdate)
                .map(userEntity -> UserUtils.convertUserResponseSave(String.valueOf(userEntity.getId())))
                .doOnError(error -> log.error(Constantes.SAVE_ERROR, error.getMessage(), error))
                .onErrorResume(UserUtils::handleErrorUser);
    }

    @Override
    public Mono<UserResponse> updateUser(UserRequest userRequest) {
        return userRepositoryOutputPort
                .findById(userRequest.getId())
                .map(userExisting -> UserUtils.convertUserEntityUpdate(userRequest))
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario con id " + userRequest.getId())))
                .flatMap(userRepositoryOutputPort::saveUserOrUpdate)
                .map(userEntity -> UserUtils.convertUserResponseUpdate(String.valueOf(userEntity.getId())))
                .doOnError(error -> log.error(Constantes.UPDATE_ERROR, error.getMessage(), error))
                .onErrorResume(UserUtils::handleErrorUser);
    }

    @Override
    public Mono<UserResponse> deleteUser(Long id) {
        return userRepositoryOutputPort
                .findById(id)
                .flatMap(userExisting -> userRepositoryOutputPort
                        .deleteById(id)
                        .then(Mono.defer(() -> Mono.just(UserUtils.convertUserResponseDelete(String.valueOf(id)))))
                )
                .switchIfEmpty(Mono.error(new UserNotFoundException("Usuario con id " + id + " no encontrado")))
                .doOnError(error -> log.error(Constantes.DELETE_ERROR, error.getMessage(), error))
                .onErrorResume(UserUtils::handleErrorUser);
    }
}
