package code.fl.proyectoredsocial.application.service;

import code.fl.proyectoredsocial.application.port.in.PostInputPort;
import code.fl.proyectoredsocial.application.port.out.PostRepositoryOutputPort;
import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.domain.model.PostListResponse;
import code.fl.proyectoredsocial.domain.model.PostResponse;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import code.fl.proyectoredsocial.infraestructure.model.PostRequest;
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
    private final UserRepositoryOutputPort userRepositoryOutputPort;

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
    public Mono<PostResponse> savePost(PostRequest postRequest) {
        return userRepositoryOutputPort.findById(postRequest.getUsuarioId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El usuario con ID " + postRequest.getUsuarioId() + " no existe")))
                .flatMap(user -> {
                    PostEntity postEntity = PostUtils.convertPostEntity(postRequest);
                    return postRepositoryOutputPort.savePostOrUpdate(postEntity);
                })
                .map(savedPost -> PostUtils.convertPostResponseSave(String.valueOf(savedPost.getId())))
                .doOnError(error -> log.error("Error en savePost(): {}", error.getMessage(), error))
                .onErrorResume(PostUtils::handleErrorPostMono);
    }


    @Override
    public Mono<PostResponse> updatePost(PostRequest postRequest) {
        return postRepositoryOutputPort
                .findById(postRequest.getId())
                .map(existingPost -> PostUtils.convertPostEntityUpdate(postRequest))
                .switchIfEmpty(Mono.error(new RuntimeException("Post con id " + postRequest.getId() + " no encontrado")))
                .flatMap(postRepositoryOutputPort::savePostOrUpdate)
                .map(updatedPost -> PostUtils.convertPostResponseSave(String.valueOf(updatedPost.getId())))
                .doOnError(error -> log.error("Error en updatePost(): {}", error.getMessage(), error))
                .onErrorResume(PostUtils::handleErrorPostMono);
    }

    @Override
    public Mono<PostResponse> deletePost(Long id) {
        return postRepositoryOutputPort
                .findById(id)
                .flatMap(existingPost -> postRepositoryOutputPort
                        .deleteById(id)
                        .then(Mono.just(PostUtils.convertPostResponseDelete(String.valueOf(id))))
                )
                .switchIfEmpty(Mono.error(new RuntimeException("Post con id " + id + " no encontrado")))
                .doOnError(error -> log.error("Error en deletePost(): {}", error.getMessage(), error))
                .onErrorResume(PostUtils::handleErrorPostMono);
    }
}
