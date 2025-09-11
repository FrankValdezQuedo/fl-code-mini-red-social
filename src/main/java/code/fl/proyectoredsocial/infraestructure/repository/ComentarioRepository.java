package code.fl.proyectoredsocial.infraestructure.repository;

import code.fl.proyectoredsocial.infraestructure.entity.ComentarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ComentarioRepository extends ReactiveCrudRepository<ComentarioEntity, Long> {
    Flux<ComentarioEntity> findAllByUsuarioId(Long usuarioId);
    Flux<ComentarioEntity> findAllByPostId(Long postId);
}

