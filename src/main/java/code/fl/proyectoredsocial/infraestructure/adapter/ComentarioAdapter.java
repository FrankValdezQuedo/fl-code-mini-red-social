package code.fl.proyectoredsocial.infraestructure.adapter;

import code.fl.proyectoredsocial.application.port.out.ComentarioRepositoryOutputPort;
import code.fl.proyectoredsocial.infraestructure.entity.ComentarioEntity;
import code.fl.proyectoredsocial.infraestructure.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ComentarioAdapter implements ComentarioRepositoryOutputPort {
    private final ComentarioRepository repository;

    @Override
    public Flux<ComentarioEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<ComentarioEntity> findAllByUsuarioId(Long usuarioId) {
        return repository.findAllByUsuarioId(usuarioId);
    }

    @Override
    public Flux<ComentarioEntity> findAllByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public Mono<ComentarioEntity> saveComentario(ComentarioEntity comentarioEntity) {
        return repository.save(comentarioEntity);
    }

    @Override
    public Mono<ComentarioEntity> updateComentario(ComentarioEntity comentarioEntity) {
        return repository.save(comentarioEntity);
    }

    @Override
    public Mono<Void> deleteComentario(Long id) {
        return repository.deleteById(id);
    }
}

