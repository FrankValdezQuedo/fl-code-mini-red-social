package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import reactor.core.publisher.Mono;

public interface PostInputPort {
    Mono<PostListResponse> findAll();
    Mono<PostListResponse> findAllByUsuarioId(Long id);
    Mono<PostEntity> savePost(PostEntity postsEntity);
    Mono<PostEntity> updatePost(PostEntity postsEntity);
    Mono<PostEntity> deletePost(Long id);
}
