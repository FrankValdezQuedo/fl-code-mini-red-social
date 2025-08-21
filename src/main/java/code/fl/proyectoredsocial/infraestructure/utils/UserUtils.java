package code.fl.proyectoredsocial.infraestructure.utils;

import code.fl.proyectoredsocial.domain.error.UserErrorFactory;
import code.fl.proyectoredsocial.domain.model.User;
import code.fl.proyectoredsocial.domain.model.UserListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.model.UserRequest;
import reactor.core.publisher.Mono;

import java.util.Collections;

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
                .biografia(entity.getBiografia())
                .build();
    }

    public static Mono<UserListResponse> handleErrorUserMono(Throwable error) {
        return Mono.error(UserErrorFactory.createException(error));
    }

}
