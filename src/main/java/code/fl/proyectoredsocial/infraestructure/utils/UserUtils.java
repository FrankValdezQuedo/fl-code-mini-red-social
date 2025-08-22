package code.fl.proyectoredsocial.infraestructure.utils;

import code.fl.proyectoredsocial.domain.error.UserErrorFactory;
import code.fl.proyectoredsocial.domain.model.User;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.domain.model.UserResponse;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Slf4j
public class UserUtils {

    public static UserListResponse convertUserSingletonResponse(UserEntity entity) {
        return UserListResponse.builder()
                .data(Collections.singletonList(convertUserResponse(entity)))
                .build();
    }

    public static User convertUserResponse (UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .rol(entity.getRol())
                .nombre(entity.getNombre())
                .biografia(entity.getBiografia())
                .build();
    }

    public static UserEntity convertUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .rol(userRequest.getRol())
                .nombre(userRequest.getNombre())
                .biografia(userRequest.getBiografia())
                .build();
    }

    public static UserEntity convertUserEntityUpdate(UserRequest userRequest) {
        return UserEntity.builder()
                .id(userRequest.getId())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .rol(userRequest.getRol())
                .nombre(userRequest.getNombre())
                .biografia(userRequest.getBiografia())
                .build();
    }

    public static UserListResponse convertCustomerListResponse(List<UserEntity> entity) {
        return UserListResponse.builder()
                .data(entity.stream()
                        .map(UserUtils::convertUserResponse)
                        .toList())
                .build();
    }

    public static UserResponse convertUserResponseSave (String idUser) {
        return UserResponse.builder()
                .codResponse(Constantes.COD_RESPONSE)
                .messageResponse(Constantes.USER_SAVED)
                .codEntity(idUser)
                .build();
    }

    public static <T> Mono<T> handleErrorUserMono(Throwable error) {
        log.error("Error procesando request: {}", error.getMessage(), error);
        return Mono.error(UserErrorFactory.createException(error));
    }
    public static Mono<UserResponse> handleErrorCustomer(Throwable error) {
        return Mono.error(UserErrorFactory.createException(error));
    }

}
