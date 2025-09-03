package code.fl.proyectoredsocial.infraestructure.adapter;

import code.fl.proyectoredsocial.application.port.out.PostRepositoryOutputPort;
import code.fl.proyectoredsocial.infraestructure.entity.PostEntity;
import code.fl.proyectoredsocial.infraestructure.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostAdapter implements PostRepositoryOutputPort {
    private final PostRepository repository;
    @Override
    public Flux<PostEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<PostEntity> findById(Long id) {
        return null;
    }

    @Override
    public Flux<PostEntity> findAllByUsuarioId(Long id) {
        return repository.findAllByUsuarioId(id);
    }

    @Override
    public Mono<PostEntity> savePostOrUpdate(PostEntity postsEntity) {
        return repository.save(postsEntity);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
