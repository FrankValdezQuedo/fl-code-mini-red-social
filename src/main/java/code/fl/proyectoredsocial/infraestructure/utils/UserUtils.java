package code.fl.proyectoredsocial.infraestructure.utils;

import code.fl.proyectoredsocial.domain.error.UserErrorFactory;
import code.fl.proyectoredsocial.domain.model.User;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
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

    public static UserListResponse convertCustomerListResponse(List<UserEntity> entity) {
        return UserListResponse.builder()
                .data(entity.stream()
                        .map(UserUtils::convertUserResponse)
                        .toList())
                .build();
    }

    public static <T> Mono<T> handleErrorUserMono(Throwable error) {
        log.error("Error procesando request: {}", error.getMessage(), error);
        return Mono.error(UserErrorFactory.createException(error));
    }

}
