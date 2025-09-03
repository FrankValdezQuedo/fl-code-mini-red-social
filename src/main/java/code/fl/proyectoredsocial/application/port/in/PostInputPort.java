package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.domain.model.PostResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import code.fl.proyectoredsocial.infraestructure.model.PostRequest;
import reactor.core.publisher.Mono;

public interface PostInputPort {
    Mono<PostListResponse> findAll();
    Mono<PostListResponse> findAllByUsuarioId(Long id);
    Mono<PostResponse> savePost(PostRequest postRequest);
    Mono<PostResponse> updatePost(PostRequest postRequest);
    Mono<PostResponse> deletePost(Long id);
}
