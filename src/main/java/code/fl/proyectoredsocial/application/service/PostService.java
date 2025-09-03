package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.in.PostInputPort;
import code.fl.proyectoredsocial.application.port.out.PostRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import code.fl.proyectoredsocial.infraestructure.utils.PostUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class PostService implements PostInputPort {

    private final PostRepositoryOutputPort postRepositoryOutputPort;

    @Override
    public Mono<PostListResponse> findAll() {
        return postRepositoryOutputPort
                .findAll()
                .collectList()
                .map(PostUtils::covertPostListResponse)
                .doOnError(error-> log.error("Error en findAll(): {}", error.getMessage()))
                .onErrorResume(PostUtils::handleErrorPostMono);
    }

    @Override
    public Mono<PostListResponse> findAllByUsuarioId(Long id) {
        return postRepositoryOutputPort
                .findAllByUsuarioId(id)
                .collectList()
                .map(PostUtils::covertPostListResponse)
                .doOnError(error -> log.error("Error en findAllByUsuarioId(): {}", error.getMessage()))
                .onErrorResume(PostUtils::handleErrorPostMono);
    }

    @Override
    public Mono<PostEntity> savePost(PostEntity postsEntity) {
        return null;
    }

    @Override
    public Mono<PostEntity> updatePost(PostEntity postsEntity) {
        return null;
    }

    @Override
    public Mono<PostEntity> deletePost(Long id) {
        return null;
    }
}
