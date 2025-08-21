package code.fl.proyectoredsocial.infraestructure.adapter;

import code.fl.proyectoredsocial.application.port.out.UserRepositoryOutputPort;
import code.fl.proyectoredsocial.infraestructure.entity.UserEntity;
import code.fl.proyectoredsocial.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserRepositoryOutputPort {

    private final UserRepository userRepository;


    @Override
    public Mono<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<UserEntity> findAll() {
        return null;
    }

    @Override
    public Mono<UserEntity> saveUser(UserEntity userEntity) {
        return null;
    }
}
