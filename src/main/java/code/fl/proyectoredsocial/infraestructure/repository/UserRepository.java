package code.fl.proyectoredsocial.infraestructure.repository;

import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

}