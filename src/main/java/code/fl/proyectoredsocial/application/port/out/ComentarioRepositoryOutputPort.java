package code.fl.proyectoredsocial.application.port.out;

import code.fl.proyectoredsocial.infraestructure.entity.ComentarioEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ComentarioRepositoryOutputPort {
    Flux<ComentarioEntity> findAll();

    Flux<ComentarioEntity> findAllByUsuarioId(Long usuarioId);

    Flux<ComentarioEntity> findAllByPostId(Long postId);

    Mono<ComentarioEntity> saveComentario(ComentarioEntity comentarioEntity);

    Mono<ComentarioEntity> updateComentario(ComentarioEntity comentarioEntity);

    Mono<Void> deleteComentario(Long id);
}

