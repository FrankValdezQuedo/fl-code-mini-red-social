package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.domain.model.UserResponse;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
import reactor.core.publisher.Mono;

public interface UserInputPort {
    Mono<UserListResponse> findById(Long id);
    Mono<UserListResponse> findAll();
    Mono<UserResponse> saveUser(UserRequest userRequest);
    Mono<UserResponse> updateUser(UserRequest userRequest);
    Mono<UserResponse> deleteUser(Long id);
}