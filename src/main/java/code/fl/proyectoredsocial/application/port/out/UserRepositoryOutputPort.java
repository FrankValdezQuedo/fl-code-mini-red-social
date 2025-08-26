package code.fl.proyectoredsocial.application.port.out;

import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepositoryOutputPort {
    Mono<UserEntity> findById(Long id);
    Flux<UserEntity> findAll();
    Mono<UserEntity> saveUserOrUpdate(UserEntity userEntity);
    Mono<Void> deleteById(Long id);
}
