package code.fl.proyectoredsocial.infraestructure.utils;

import code.fl.proyectoredsocial.domain.error.UserErrorFactory;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.domain.model.Post;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class PostUtils {

    public static PostListResponse covertPostListResponse(List<PostEntity> entity) {
        return PostListResponse.builder()
                .data(entity.stream()
                        .map(PostUtils::convertPostResponse)
                        .toList())
                .build();
    }

    public static Post convertPostResponse(PostEntity entity) {
        return Post.builder()
                .id(entity.getId())
                .contenido(entity.getContenido())
                .imagenUrl(entity.getImagenUrl())
                .usuarioId(entity.getUsuarioId())
                .fecha(entity.getFecha())
                .build();
    }

    public static <T> Mono<T> handleErrorPostMono(Throwable error) {
        log.error("Error procesando request: {}", error.getMessage(), error);
        return Mono.error(UserErrorFactory.createException(error));
    }
}
