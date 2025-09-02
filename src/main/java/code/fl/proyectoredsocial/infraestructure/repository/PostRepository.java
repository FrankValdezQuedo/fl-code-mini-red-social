package code.fl.proyectoredsocial.infraestructure.repository;

import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PostRepository extends ReactiveCrudRepository<PostEntity, Long> {
    Flux<PostEntity> findAllByUsuarioId(Long usuarioId);
}
