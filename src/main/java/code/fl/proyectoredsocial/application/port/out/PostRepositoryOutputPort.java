package code.fl.proyectoredsocial.application.port.out;

import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepositoryOutputPort {
    Flux<PostEntity> findAll ();
    Mono<PostEntity> findById(Long id);
    Flux<PostEntity> findAllByUsuarioId(Long id);
    Mono<PostEntity> savePostOrUpdate(PostEntity postsEntity);
    Mono<Void> deleteById(Long id);

}
