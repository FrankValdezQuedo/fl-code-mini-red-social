package code.fl.proyectoredsocial.infraestructure.repository;

import code.fl.proyectoredsocial.infraestructure.entity.PostsEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostsRepository extends ReactiveCrudRepository<PostsEntity, Long> {
}
