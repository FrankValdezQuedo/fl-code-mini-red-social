package code.fl.proyectoredsocial.application.port.in;

import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostsEntity;
import reactor.core.publisher.Mono;

public interface PostInputPort {
    Mono<PostListResponse> findAll();
    Mono<PostListResponse> findById(Long id);
    Mono<PostsEntity> savePost(PostsEntity postsEntity);
    Mono<PostsEntity> updatePost(PostsEntity postsEntity);
    Mono<PostsEntity> deletePost(Long id);
}
